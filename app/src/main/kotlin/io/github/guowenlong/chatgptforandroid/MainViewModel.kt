package io.github.guowenlong.chatgptforandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.github.guowenlong.chatgpt.StreamListener
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.EditRequest
import io.github.guowenlong.chatgpt.model.request.EmbeddingsRequest
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgptforandroid.common.base.BaseViewModel
import io.github.guowenlong.chatgptforandroid.common.ext.logE
import io.github.guowenlong.chatgptforandroid.repository.ChatGPTRepository
import kotlinx.coroutines.launch
import java.io.File

/**
 * Description: [MainActivity]的ViewModel
 * Author:      郭文龙
 * Date:        2023/3/31 2:38
 * Email:       guowenlong20000@sina.com
 */
class MainViewModel(private val repository: ChatGPTRepository) : BaseViewModel() {

    val data = mutableListOf<Any>()

    private val _updateLiveData = MutableLiveData<Int>()
    val updateLiveData: LiveData<Int> = _updateLiveData

    private val _insertUserChatLiveData = MutableLiveData<String>()
    val insertUserChatLiveData: LiveData<String> = _insertUserChatLiveData

    private val _insertGPTChatLiveData = MutableLiveData<CompletionStream>()
    val insertGPTChatLiveData: LiveData<CompletionStream> = _insertGPTChatLiveData

    fun getModels() = launch {
        val models = repository.getModels()
        logE("getModels: $models")
    }

    fun completions() = launch {
        val completions = repository.getCompletions(
            CompletionRequest(
                messages = listOf(
                    CompletionRequest.Message(
                        content = "埃隆马斯克的生平简介发给我"
                    )
                )
            )
        )
        logE("completions: $completions")
    }

    fun completionStream(completionRequest: CompletionRequest) = launch {
        repository.getCompletionsByString(
            completionRequest, object : StreamListener {
                override fun onStart() {
                    logE("onStart")
                    data.add(completionRequest.messages[0].content)
                    _insertUserChatLiveData.postValue(completionRequest.messages[0].content)
                }

                override fun onStreamPre(completionStream: CompletionStream) {
                    logE("onStreamPre $completionStream")
                    data.add(completionStream)
                    _insertGPTChatLiveData.postValue(completionStream)
                }

                override fun onStream(completionStream: CompletionStream) {
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
                    logE("onStream $completionStream")
                }

                override fun onCompleted() {
                    logE("onCompleted")
                }

                override fun onError(exception: Exception) {
                    logE("onError:", exception)
                }
            }
        )
    }

    fun edits() = launch {
        val edits = repository.getEdits(
            EditRequest(
                model = "text-davinci-edit-001",
                input = "What day of the wek is it?",
                instruction = "Fix the spelling mistakes"
            )
        )
        logE("edits: $edits")
    }

    fun generationImage() = launch {
        val generationImage = repository.generationImage(
            ImageGenerationRequest(
                prompt = "A cute West Highland",
                n = 2,
                size = "1024x1024"
            )
        )
        logE("generationImage: $generationImage")
    }

    fun editImage(image: File) = launch {
        val imageGeneration = repository.editImage(
            image = image,
            prompt = "Grayscale",
            size = "256x256"
        )
        logE("editImage: $imageGeneration")
    }

    fun variationImage(image: File) = launch {
        val imageGeneration = repository.variationImage(
            image = image,
            size = "256x256",
            n = 2
        )
        logE("editImage: $imageGeneration")
    }

    fun embeddings() = launch {
        val embeddings = repository.getEmbeddings(
            EmbeddingsRequest(
                model = "text-embedding-ada-002",
                input = "The food was delicious and the waiter",
            )
        )
        logE("embeddings: $embeddings")
    }

    fun translation(file: File) = launch {
        val translation = repository.translation(
            file = file,
            model = "whisper-1",
            language = "zh",
        )
        logE("translation: $translation")
    }
}