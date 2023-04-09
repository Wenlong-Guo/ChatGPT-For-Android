package io.github.guowenlong.chatgptforandroid.common.components

import io.github.guowenlong.chatgptforandroid.common.base.BaseSharedPreferences

/**
 * Description: SharedPreferences
 * Author:      郭文龙
 * Date:        2023/3/30 0:27
 * Email:       guowenlong20000@sina.com
 */
class SpProperty : BaseSharedPreferences() {
    var openAPIKey by Delegates.string()
    var openAPIHost by Delegates.string("https://api.openai.com/")

    var proxyType by Delegates.int(0)
    var proxyHost by Delegates.string()
    var proxyPort by Delegates.int(7890)

    var chatTemperature by Delegates.float(1.0F)
    var chatTopP by Delegates.float(1.0F)
    var chatMaxToken by Delegates.int(2048)
    var chatPresencePenalty by Delegates.float(0.0F)
    var chatFrequencyPenalty by Delegates.float(0.0F)
    var chatContextCount by Delegates.int(3)

}