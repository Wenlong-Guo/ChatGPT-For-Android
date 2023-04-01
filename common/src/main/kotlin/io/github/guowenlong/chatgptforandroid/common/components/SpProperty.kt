package io.github.guowenlong.chatgptforandroid.common.components

import io.github.guowenlong.chatgptforandroid.common.base.BaseSharedPreferences

/**
 * Description: SharedPreferences
 * Author:      郭文龙
 * Date:        2023/3/30 0:27
 * Email:       guowenlong20000@sina.com
 */
class SpProperty: BaseSharedPreferences(){
    var openAPIKey by Delegates.string()
}