package io.github.guowenlong.chatgpt.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Description: OpenAI模型
 * Author:      郭文龙
 * Date:        2023/3/31 0:58
 * Email:       guowenlong20000@sina.com
 */
@JsonClass(generateAdapter = true)
data class Model(
    @field:Json(name = "data")
    val `data`: List<Data>,
    @field:Json(name = "object")
    val `object`: String
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @field:Json(name = "id")
        val id: String,
        @field:Json(name = "object")
        val `object`: String,
        @field:Json(name = "owned_by")
        val owned_by: String,
        @field:Json(name = "permission")
        val permission: List<Permission>,
        @field:Json(name = "root")
        val root: String,
    ) {
        @JsonClass(generateAdapter = true)
        data class Permission(
            @field:Json(name = "allow_create_engine")
            val allow_create_engine: Boolean,
            @field:Json(name = "allow_fine_tuning")
            val allow_fine_tuning: Boolean,
            @field:Json(name = "allow_logprobs")
            val allow_logprobs: Boolean,
            @field:Json(name = "allow_sampling")
            val allow_sampling: Boolean,
            @field:Json(name = "allow_search_indices")
            val allow_search_indices: Boolean,
            @field:Json(name = "allow_view")
            val allow_view: Boolean,
            @field:Json(name = "created")
            val created: Int,
            @field:Json(name = "group")
            val group: Any? = null,
            @field:Json(name = "id")
            val id: String,
            @field:Json(name = "is_blocking")
            val is_blocking: Boolean,
            @field:Json(name = "object")
            val `object`: String,
            @field:Json(name = "organization")
            val organization: String
        )
    }
}