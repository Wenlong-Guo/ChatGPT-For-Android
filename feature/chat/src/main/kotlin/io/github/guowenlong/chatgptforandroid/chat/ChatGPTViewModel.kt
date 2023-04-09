package io.github.guowenlong.chatgptforandroid.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgpt.StreamListener
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgptforandroid.common.base.BaseViewModel
import io.github.guowenlong.chatgptforandroid.common.base.Status
import io.github.guowenlong.chatgptforandroid.common.ext.logE
import io.github.guowenlong.chatgptforandroid.common.ext.logI
import io.github.guowenlong.chatgptforandroid.model.UserChat
import io.github.guowenlong.chatgptforandroid.repository.ChatGPTRepository
import kotlinx.coroutines.launch

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

    fun completionStream(completionRequest: CompletionRequest) = launch {
        repository.getCompletionsByString(
            completionRequest, object : StreamListener {
                override fun onStart() {
                    logI("onStart")
                    _statusLiveData.postValue(Status.Loading)
                    UserChat(
                        content = completionRequest.messages[completionRequest.messages.size-1].content,
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
}