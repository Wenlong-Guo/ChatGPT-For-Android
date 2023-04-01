package io.github.guowenlong.chatgptforandroid.model

/**
 * Description: OpenAI模型
 * Author:      郭文龙
 * Date:        2023/3/31 0:58
 * Email:       guowenlong20000@sina.com
 */
data class Model(
    val `data`: List<Data>,
    val `object`: String
) {
    data class Data(
        val id: String,
        val `object`: String,
        val owned_by: String,
        val permission: List<Permission>,
        val root: String,
    ) {
        data class Permission(
            val allow_create_engine: Boolean,
            val allow_fine_tuning: Boolean,
            val allow_logprobs: Boolean,
            val allow_sampling: Boolean,
            val allow_search_indices: Boolean,
            val allow_view: Boolean,
            val created: Int,
            val group: Any,
            val id: String,
            val is_blocking: Boolean,
            val `object`: String,
            val organization: String
        )
    }
}