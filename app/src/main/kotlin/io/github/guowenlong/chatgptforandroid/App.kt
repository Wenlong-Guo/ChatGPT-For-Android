package io.github.guowenlong.chatgptforandroid

import io.github.guowenlong.SingleChatGPT
import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgptforandroid.common.base.BaseApplication
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent
import java.net.Proxy

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
        if (sp.openAPIKey.isNotBlank()) {
            SingleChatGPT.instance = ChatGPT.Builder()
                .setReadTimeout(2 * 60_000)
                .setWriteTimeout(2 * 60_000)
                .setConnectTimeout(2 * 60_000)
                .setBaseUrl(sp.openAPIHost)
                .setApiKeys(sp.openAPIKey)
                .also { builder ->
                    val type = when (sp.proxyType) {
                        1 -> Proxy.Type.HTTP
                        2 -> Proxy.Type.SOCKS
                        3 -> Proxy.Type.DIRECT
                        else -> null
                    }
                    type?.let {
                        builder.setProxyUrl(type, sp.proxyHost, sp.proxyPort)
                    }
                }
                .setApiKeys(sp.openAPIKey)
                .setLogEnable(true)
                .build()
        }
        if (sp.openAPIKey.isBlank()) sp.openAPIKey = io.github.guowenlong.chatgptforandroid.common.BuildConfig.apiKye
    }
}