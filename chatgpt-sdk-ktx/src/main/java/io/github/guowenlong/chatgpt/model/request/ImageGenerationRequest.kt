package io.github.guowenlong.chatgpt.model.request


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 图片生成请求参数 (Beta)
 * Author:      郭文龙
 * Date:        2023/4/4 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class ImageGenerationRequest(
    @Json(name = "prompt")
    val prompt: String,
    @Json(name = "n")
    val n: Int? = null,
    //['256x256', '512x512', '1024x1024']
    @Json(name = "size")
    val size: String? = null,
    @Json(name = "response_format")
    val response_format: String? = null,
    @Json(name = "user")
    val user: String? = null
)