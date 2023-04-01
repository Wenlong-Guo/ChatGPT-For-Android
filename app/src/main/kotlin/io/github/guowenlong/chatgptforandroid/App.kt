package io.github.guowenlong.chatgptforandroid

import io.github.guowenlong.chatgptforandroid.common.base.BaseApplication
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent

/**
 * Description: 应用
 * Author:      郭文龙
 * Date:        2023/3/30 0:56
 * Email:       guowenlong20000@sina.com
 */
class App : BaseApplication() {

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModules)
        }
        sp.init(this)
        if (sp.openAPIKey.isNullOrEmpty()) sp.openAPIKey = io.github.guowenlong.chatgptforandroid.common.BuildConfig.apiKye
    }
}