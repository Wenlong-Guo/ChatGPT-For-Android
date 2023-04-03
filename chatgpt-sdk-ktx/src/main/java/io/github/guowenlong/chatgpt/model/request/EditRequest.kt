package io.github.guowenlong.chatgpt.model.request

import com.squareup.moshi.JsonClass

/**
 * Description: 聊天请求参数
 * Author:      郭文龙
 * Date:        2023/4/4 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class EditRequest(
    val model: String,
    val instruction: String,
    val input: String? = null,
    val n: Int? = null,
    val temperature: Double? = null,
    val top_p: Double? = null,
)