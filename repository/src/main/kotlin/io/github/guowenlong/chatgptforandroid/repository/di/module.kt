package io.github.guowenlong.chatgptforandroid.repository.di

import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.repository.ChatGPTRepository
import org.koin.dsl.module

/**
 * Description: Repository模块的依赖注入
 * Author:      郭文龙
 * Date:        2023/3/30 0:12
 * Email:       guowenlong20000@sina.com
 */
val repositoryModule = module {
    //sp init
    single { SpProperty() }
    single { ChatGPTRepository() }
}
