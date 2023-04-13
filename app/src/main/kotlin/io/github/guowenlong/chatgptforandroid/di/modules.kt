package io.github.guowenlong.chatgptforandroid.di

import io.github.guowenlong.chatgptforandroid.chat.dl.chatModule
import io.github.guowenlong.chatgptforandroid.common.di.baseModule
import io.github.guowenlong.chatgptforandroid.repository.di.repositoryModule
import org.koin.dsl.module

/**
 * Description: mainModule 和 appModule
 * Author:      郭文龙
 * Date:        2023/3/29 22:54
 * Email:       guowenlong20000@sina.com
 */
val mainModule = module {

}

val appModules = listOf(
    baseModule,
    mainModule,
    repositoryModule,
    chatModule,
)