package io.github.guowenlong.chatgpt.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: 图片生成响应实体类 (Beta)
 * Author:      郭文龙
 * Date:        2023/4/4 2:02
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class ImageGeneration(
    @Json(name = "created")
    val created: Int,
    @Json(name = "data")
    val `data`: List<Data>
){
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "url")
        val url: String
    )
}