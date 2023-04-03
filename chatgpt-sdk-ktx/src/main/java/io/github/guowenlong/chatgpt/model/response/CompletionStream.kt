package io.github.guowenlong.chatgpt.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 流式响应聊天实体类
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class CompletionStream(
    @field:Json(name = "choices")
    val choices: List<Choice>,
    @field:Json(name = "created")
    val created: Int,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "model")
    val model: String,
    @field:Json(name = "object")
    val `object`: String? = null,
) {
    @JsonClass(generateAdapter = true)
    data class Choice(
        @field:Json(name = "finish_reason")
        val finish_reason: String? = null,
        @field:Json(name = "index")
        val index: Int,
        @field:Json(name = "delta")
        val delta: Delta,
    ) {
        @JsonClass(generateAdapter = true)
        data class Delta(
            @field:Json(name = "content")
            var content: String = ""
        )
    }
}
