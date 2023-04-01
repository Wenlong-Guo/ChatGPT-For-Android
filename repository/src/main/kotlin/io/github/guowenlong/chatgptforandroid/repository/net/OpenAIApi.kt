package io.github.guowenlong.chatgptforandroid.repository.net

import io.github.guowenlong.chatgptforandroid.model.Completion
import io.github.guowenlong.chatgptforandroid.model.CompletionRequest
import io.github.guowenlong.chatgptforandroid.model.Model
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description: 请求
 * Author:      郭文龙
 * Date:        2023/3/31 0:52
 * Email:       guowenlong20000@sina.com
 */
interface OpenAIApi {
    /**
     * 获取模型列表
     */
    @GET("/v1/models")
    suspend fun getModels(): Model

    @POST("/v1/chat/completions")
    suspend fun completions(@Body body: CompletionRequest): Completion
}