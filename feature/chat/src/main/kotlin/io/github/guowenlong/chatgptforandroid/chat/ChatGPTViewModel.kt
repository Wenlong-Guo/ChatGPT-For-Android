package io.github.guowenlong.chatgptforandroid.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgpt.StreamListener
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgpt.model.response.ImageGeneration
import io.github.guowenlong.chatgpt.model.response.Translation
import io.github.guowenlong.chatgptforandroid.common.base.BaseViewModel
import io.github.guowenlong.chatgptforandroid.common.base.Status
import io.github.guowenlong.chatgptforandroid.common.ext.logE
import io.github.guowenlong.chatgptforandroid.common.ext.logI
import io.github.guowenlong.chatgptforandroid.model.UserChat
import io.github.guowenlong.chatgptforandroid.model.UserTranslation
import io.github.guowenlong.chatgptforandroid.repository.ChatGPTRepository
import kotlinx.coroutines.launch
import java.io.File

/**
 * Description: [ChatGPT]相关的[ViewModel]
 * Author:      郭文龙
 * Date:        2023/4/7 1:46
 * Email:       guowenlong20000@sina.com
 */
class ChatGPTViewModel(private val repository: ChatGPTRepository) : BaseViewModel() {

    val data = mutableListOf<Any>()

    private val _updateLiveData = MutableLiveData<Int>()
    val updateLiveData: LiveData<Int> = _updateLiveData

    private val _insertUserChatLiveData = MutableLiveData<String>()
    val insertUserChatLiveData: LiveData<String> = _insertUserChatLiveData

    private val _insertGPTChatLiveData = MutableLiveData<CompletionStream>()
    val insertGPTChatLiveData: LiveData<CompletionStream> = _insertGPTChatLiveData

    private val _statusLiveData = MutableLiveData<Status<*>>()
    val statusLiveData: LiveData<Status<*>> = _statusLiveData

    fun completionStreamWithContext(completionRequest: CompletionRequest, count: Int) = launch {
        var temp = 0
        mutableListOf<CompletionRequest.Message>().also { messages ->
            messages.add(completionRequest.messages[0])
            data.asReversed().map {
                if (count < temp) return@map
                when (it) {
                    is UserChat -> {
                        temp++
                        messages.add(CompletionRequest.Message(role = "user", content = it.content))
                    }
                    is CompletionStream -> {
                        messages.add(
                            CompletionRequest.Message(
                                role = "assistant",
                                content = it.choices[0].delta.content
                            )
                        )
                    }
                    else -> {
                        logE("未知类型")
                    }
                }
            }
        }.let {
            completionRequest.messages = it.asReversed().toList()
            completionStream(completionRequest)
            logE("completionRequest ${completionRequest.messages}")
        }
    }

    private fun completionStream(completionRequest: CompletionRequest) = launch {
        completionRequest.temperature = sp.chatTemperature.toDouble()
        completionRequest.top_p = sp.chatTopP.toDouble()
        if (sp.chatMaxToken != 2048) {
            completionRequest.max_tokens = sp.chatMaxToken
        }
        completionRequest.presence_penalty = sp.chatPresencePenalty.toDouble()
        completionRequest.frequency_penalty = sp.chatFrequencyPenalty.toDouble()
        repository.getCompletionsByString(
            completionRequest, object : StreamListener {
                override fun onStart() {
                    logI("onStart")
                    _statusLiveData.postValue(Status.Loading)
                    UserChat(
                        content = completionRequest.messages[completionRequest.messages.size - 1].content,
                        time = System.currentTimeMillis()
                    ).let {
                        data.add(it)
                        _insertUserChatLiveData.postValue(it.content)
                    }
                }

                override fun onStreamPre(completionStream: CompletionStream) {
                    logI("onStreamPre $completionStream")
                    completionStream.time = System.currentTimeMillis()
                    data.add(completionStream)
                    _insertGPTChatLiveData.postValue(completionStream)
                }

                override fun onStream(completionStream: CompletionStream) {
                    logI("onStream $completionStream")
                    viewModelScope.launch {
                        data.mapIndexed { index, any ->
                            if (any is CompletionStream) {
                                if (any.id == completionStream.id) {
                                    any.choices[0].delta.content += completionStream.choices[0].delta.content
                                    _updateLiveData.postValue(index)
                                }
                            }
                        }
                    }
                }

                override fun onCompleted() {
                    _statusLiveData.postValue(Status.Completed)
                    logI("onCompleted")
                }

                override fun onError(exception: Exception) {
                    _statusLiveData.postValue(Status.Error(exception))
                    logE("onError $exception")
                }
            }
        )
    }

    private val _insertImageGPTChatLiveData = MutableLiveData<ImageGeneration>()
    val insertImageGPTChatLiveData: LiveData<ImageGeneration> = _insertImageGPTChatLiveData

    fun createImage(imageGenerationRequest: ImageGenerationRequest) = launch {
        val time = System.currentTimeMillis()
        _statusLiveData.postValue(Status.Loading)
        UserChat(
            content = imageGenerationRequest.prompt,
            time = time
        ).let {
            data.add(it)
            _insertUserChatLiveData.postValue(it.content)
        }
        val image = repository.generationImage(imageGenerationRequest)
        data.add(image)
        _insertImageGPTChatLiveData.postValue(image)
        _statusLiveData.postValue(Status.Completed)
    }

    private val _insertTranslationGPTChatLiveData = MutableLiveData<Translation>()
    val insertTranslationGPTChatLiveData: LiveData<Translation> = _insertTranslationGPTChatLiveData

    fun translation(
        file: File, language: String = "zh",
        prompt: String? = null,
        response_format: String? = null,
        temperature: Double? = null,
    ) = launch {
        val time = System.currentTimeMillis()
        _statusLiveData.postValue(Status.Loading)
        UserTranslation(
            prompt = prompt,
            filePath = file.absolutePath,
            time = time
        ).let {
            data.add(it)
            _insertUserChatLiveData.postValue(it.filePath)
        }
        val translation = repository.translation(
            file,
            language = language,
            model = "whisper-1",
            response_format = response_format,
            temperature = temperature,
            prompt = prompt
        )
        data.add(translation)
        _insertTranslationGPTChatLiveData.postValue(translation)
        _statusLiveData.postValue(Status.Completed)
        logE("translation $translation")
    }
}