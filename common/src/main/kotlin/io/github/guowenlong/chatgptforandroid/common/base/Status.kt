package io.github.guowenlong.chatgptforandroid.common.base

/**
 * Description: 网络请求状态
 * Author:      郭文龙
 * Date:        2023/4/9 20:03
 * Email:       guowenlong20000@sina.com
 */
sealed class Status<out T> {
    object Loading : Status<Nothing>()
    object Completed : Status<Nothing>()
    data class Success<out T>(val data: T) : Status<T>()
    data class Error(val exception: Exception) : Status<Nothing>()
}
