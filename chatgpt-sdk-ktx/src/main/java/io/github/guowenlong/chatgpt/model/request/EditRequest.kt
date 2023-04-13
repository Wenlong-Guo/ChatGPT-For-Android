package io.github.guowenlong.chatgpt.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 聊天请求参数
 * Author:      郭文龙
 * Date:        2023/4/4 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class EditRequest(
    @field:Json(name = "model")
    val model: String,
    @field:Json(name = "instruction")
    val instruction: String,
    @field:Json(name = "input")
    val input: String? = null,
    @field:Json(name = "n")
    val n: Int? = null,
    @field:Json(name = "temperature")
    val temperature: Double? = null,
    @field:Json(name = "top_p")
    val topP: Double? = null,
)