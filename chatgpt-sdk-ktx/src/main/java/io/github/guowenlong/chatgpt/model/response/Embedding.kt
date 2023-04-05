package io.github.guowenlong.chatgpt.model.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: Embeddings接口返回数据
 * Author:      郭文龙
 * Date:        2023/4/6 2:40
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class Embedding(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "model")
    val model: String,
    @Json(name = "object")
    val objectX: String,
    @Json(name = "usage")
    val usage: Usage
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "embedding")
        val embedding: List<Double>,
        @Json(name = "index")
        val index: Int,
        @Json(name = "object")
        val objectX: String
    )

    @JsonClass(generateAdapter = true)
    data class Usage(
        @Json(name = "prompt_tokens")
        val promptTokens: Int,
        @Json(name = "total_tokens")
        val totalTokens: Int
    )
}