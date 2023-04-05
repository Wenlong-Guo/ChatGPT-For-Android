package io.github.guowenlong.chatgpt.api

import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.EditRequest
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgpt.model.response.Completion
import io.github.guowenlong.chatgpt.model.response.Edits
import io.github.guowenlong.chatgpt.model.response.ImageGeneration
import io.github.guowenlong.chatgpt.model.response.Model
import okhttp3.MultipartBody
import retrofit2.http.*

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

    @POST("/v1/images/generations")
    suspend fun generationImage(@Body body: ImageGenerationRequest): ImageGeneration

    @Multipart
    @POST("/v1/images/edits")
    suspend fun editImage(
        @Part image: MultipartBody.Part,
        @Part prompt: MultipartBody.Part,
        @Part mask: MultipartBody.Part? = null,
        @Part n: MultipartBody.Part? = null,
        @Part size: MultipartBody.Part? = null,
        @Part response_format: MultipartBody.Part? = null,
        @Part user: MultipartBody.Part? = null
    ): ImageGeneration

    @Multipart
    @POST("/v1/images/variations")
    suspend fun variationImage(
        @Part image: MultipartBody.Part,
        @Part n: MultipartBody.Part? = null,
        @Part size: MultipartBody.Part? = null,
        @Part response_format: MultipartBody.Part? = null,
        @Part user: MultipartBody.Part? = null
    ): ImageGeneration
}