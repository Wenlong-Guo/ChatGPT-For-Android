package io.github.guowenlong.chatgptforandroid.common.base

import android.app.Application
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.java.KoinJavaComponent

/**
 * Description: [Application] 的基类
 * Author:      郭文龙
 * Date:        2023/3/30 0:27
 * Email:       guowenlong20000@sina.com
 */
open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}