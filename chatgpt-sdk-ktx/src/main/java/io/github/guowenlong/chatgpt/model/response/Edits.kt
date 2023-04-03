package io.github.guowenlong.chatgpt.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: Edits接口返回数据实体类
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class Edits(
    @Json(name = "object")
    val `object`: String,
    @Json(name = "created")
    val created: Int,
    @Json(name = "choices")
    val choices: List<Choice>,
    @Json(name = "usage")
    val usage: Usage
) {
    @JsonClass(generateAdapter = true)
    data class Choice(
        @Json(name = "index")
        val index: Int,
        @Json(name = "text")
        val text: String
    )

    @JsonClass(generateAdapter = true)
    data class Usage(
        @Json(name = "completion_tokens")
        val completionTokens: Int,
        @Json(name = "prompt_tokens")
        val promptTokens: Int,
        @Json(name = "total_tokens")
        val totalTokens: Int
    )
}