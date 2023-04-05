package io.github.guowenlong.chatgpt.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 语音翻译文字接口返回数据
 * Author:      郭文龙
 * Date:        2023/4/6 2:40
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class Translation(
    @Json(name = "text")
    val text: String
)