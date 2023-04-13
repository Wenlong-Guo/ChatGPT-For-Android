package io.github.guowenlong.chatgptforandroid.chat.dl

import io.github.guowenlong.chatgptforandroid.chat.ChatGPTViewModel
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Description: 聊天的依赖注入module
 * Author:      郭文龙
 * Date:        2023/4/7 21:04
 * Email:       guowenlong20000@sina.com
 */

val chatModule = module {

    single { SpProperty() }
    viewModel { ChatGPTViewModel(get()) }

}