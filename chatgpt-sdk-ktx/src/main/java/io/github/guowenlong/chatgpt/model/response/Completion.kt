package io.github.guowenlong.chatgpt.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 聊天实体类
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class Completion(
    @field:Json(name = "choices")
    val choices: List<Choice>,
    @field:Json(name = "created")
    val created: Int,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "model")
    val model: String,
    @field:Json(name = "object")
    val `object`: String,
    @field:Json(name = "usage")
    val usage: Usage
){
    @JsonClass(generateAdapter = true)
    data class Choice(
        @field:Json(name = "finish_reason")
        val finish_reason: String,
        @field:Json(name = "index")
        val index: Int,
        @field:Json(name = "logprobs")
        val logprobs: Any,
        @field:Json(name = "text")
        val text: String
    )
    @JsonClass(generateAdapter = true)
    data class Usage(
        @field:Json(name = "completion_tokens")
        val completion_tokens: Int,
        @field:Json(name = "prompt_tokens")
        val prompt_tokens: Int,
        @field:Json(name = "total_tokens")
        val total_tokens: Int
    )
}