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
    @field:Json(name = "messages")
    val messages: List<Message>,
    @field:Json(name = "logprobs")
    val logprobs: Int? = null,
    @field:Json(name = "max_tokens")
    val max_tokens: Int = 3500,
    @field:Json(name = "model")
    val model: String = "gpt-3.5-turbo-0301",
    @field:Json(name = "n")
    val n: Int = 1,
    @field:Json(name = "prompt")
    val prompt: String? = null,
    @field:Json(name = "stop")
    val stop: String? = null,
    @field:Json(name = "stream")
    var stream: Boolean = true,
    @field:Json(name = "temperature")
    val temperature: Int = 1,
    @field:Json(name = "top_p")
    val top_p: Int = 1
) {
    @JsonClass(generateAdapter = true)
    data class Message(
        @field:Json(name = "role")
        val role: String = "user",
        @field:Json(name = "content")
        val content: String
    )
}