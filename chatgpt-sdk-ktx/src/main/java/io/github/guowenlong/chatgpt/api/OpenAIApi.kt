package io.github.guowenlong.chatgpt.api

import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.EditRequest
import io.github.guowenlong.chatgpt.model.response.Completion
import io.github.guowenlong.chatgpt.model.response.Edits
import io.github.guowenlong.chatgpt.model.response.Model
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

    @POST("/v1/edits")
    suspend fun edits(@Body body: EditRequest): Edits


}