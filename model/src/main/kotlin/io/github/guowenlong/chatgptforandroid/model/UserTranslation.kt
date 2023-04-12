package io.github.guowenlong.chatgptforandroid.model

/**
 * Description: 用户翻译数据
 * Author:      郭文龙
 * Date:        2023/4/12 18:18
 * Email:       guowenlong20000@sina.com
 */
data class UserTranslation(
    val filePath: String,
    val prompt: String? = null,
    val time: Long
)
