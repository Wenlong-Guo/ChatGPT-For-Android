package io.github.guowenlong.chatgptforandroid.repository

import io.github.guowenlong.chatgptforandroid.common.base.BaseRepository
import io.github.guowenlong.chatgptforandroid.model.CompletionRequest
import io.github.guowenlong.chatgptforandroid.repository.net.OpenAIApi

/**
 * Description: OpenAI的仓库
 * Author:      郭文龙
 * Date:        2023/3/31 1:26
 * Email:       guowenlong20000@sina.com
 */
class OpenAIRepository(private val api: OpenAIApi) : BaseRepository() {

    suspend fun getModels() = request { api.getModels() }

    suspend fun completions(content: String) =
        request { api.completions(CompletionRequest(messages = listOf(CompletionRequest.Message(content = content)))) }

}