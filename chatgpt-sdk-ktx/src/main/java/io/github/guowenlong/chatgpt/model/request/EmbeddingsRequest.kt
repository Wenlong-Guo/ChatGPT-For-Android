package io.github.guowenlong.chatgpt.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: Embeddings接口请求数参数
 * Author:      郭文龙
 * Date:        2023/4/6 2:40
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class EmbeddingsRequest(
    @field:Json(name = "model")
    val model: String,
    @field:Json(name = "input")
    val input: String,
    @field:Json(name = "user")
    val user: String? = null,
)
