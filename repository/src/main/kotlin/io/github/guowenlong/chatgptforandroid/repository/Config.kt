package io.github.guowenlong.chatgptforandroid.repository

/**
 * Description: 网络请求参数
 * Author:      郭文龙
 * Date:        2023/3/30 1:04
 * Email:       guowenlong20000@sina.com
 */
object Config {

    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 5 * 60L
    const val WRITE_TIMEOUT = 5 * 60L

    const val API_BASE_URL = "https://api.openai.com/"
}