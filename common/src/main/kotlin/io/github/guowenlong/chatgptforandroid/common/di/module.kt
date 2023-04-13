package io.github.guowenlong.chatgptforandroid.common.di

import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.dsl.module

/**
 * Description: base的依赖注入module
 * Author:      郭文龙
 * Date:        2023/3/30 0:29
 * Email:       guowenlong20000@sina.com
 */

val baseModule = module {

    single { SpProperty() }

}