package io.github.guowenlong.chatgptforandroid.common.ext

import android.util.Log

/**
 * Description: 自定义日志扩展类
 * Author:      郭文龙
 * Date:        2023/3/31 22:49
 * Email:       guowenlong20000@sina.com
 */


private const val elementIndex = 2
private var isEnable = true

fun initLogEnable(isLogEnable: Boolean) {
    isEnable = isLogEnable
}

fun logE(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.e(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}

fun logW(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.w(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}

fun logI(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.i(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}

fun logD(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.d(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}

fun logV(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.v(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}


fun logWtf(message: String, throwable: Throwable? = null) {
    if (!isEnable) return
    Log.wtf(
        getTag(Throwable().stackTrace),
        message,
        throwable
    )
}

private fun getTag(sElements: Array<StackTraceElement>): String =
    "(${sElements[elementIndex].fileName}:${sElements[elementIndex].lineNumber})"
