package io.github.guowenlong.chatgptforandroid.repository.di

import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgptforandroid.common.BuildConfig
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.repository.OpenAIRepository
import org.koin.dsl.module
import java.net.Proxy


/**
 * Description: Repository模块的依赖注入
 * Author:      郭文龙
 * Date:        2023/3/30 0:12
 * Email:       guowenlong20000@sina.com
 */

val repositoryModule = module {
    //sp init
    single { SpProperty() }
    single {
        ChatGPT.Builder()
            .setReadTimeout(2 * 60_000)
            .setWriteTimeout(2 * 60_000)
            .setConnectTimeout(2 * 60_000)
            .setProxyUrl(Proxy.Type.SOCKS,"192.168.50.111",7891)
            .setBaseUrl("https://api.openai.com/")
            .setApiKeys(BuildConfig.apiKye)
            .setLogEnable(true)
            .build()
    }
    single { OpenAIRepository(get()) }
}
