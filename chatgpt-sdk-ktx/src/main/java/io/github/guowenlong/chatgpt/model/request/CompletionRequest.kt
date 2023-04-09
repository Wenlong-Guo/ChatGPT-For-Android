package io.github.guowenlong.chatgpt.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 聊天请求参数
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class CompletionRequest(
    @field:Json(name = "model")
    var model: String = "gpt-3.5-turbo",
    @field:Json(name = "messages")
    var messages: List<Message>,
    @field:Json(name = "temperature")
    var temperature: Double? = null,
    @field:Json(name = "top_p")
    var top_p: Double? = null,
    @field:Json(name = "n")
    val n: Int? = null,
    @field:Json(name = "stream")
    var stream: Boolean? = false,
    @field:Json(name = "stop")
    var stop: String? = null,
    @field:Json(name = "max_tokens")
    var max_tokens: Int? = null,
    @field:Json(name = "presence_penalty")
    var presence_penalty: Double? = null,
    @field:Json(name = "frequency_penalty")
    var frequency_penalty: Double? = null,
    @field:Json(name = "logit_bias")
    var logit_bias: Any? = null,
    @field:Json(name = "user")
    var user: String? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Message(
        @field:Json(name = "role")
        val role: String = "user",
        @field:Json(name = "content")
        val content: String
    )
}