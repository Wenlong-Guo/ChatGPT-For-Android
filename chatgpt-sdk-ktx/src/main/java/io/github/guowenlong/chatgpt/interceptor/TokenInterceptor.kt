package io.github.guowenlong.chatgpt.interceptor

import io.github.guowenlong.chatgpt.utils.RandomUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description: OpenApiKey拦截器
 * Author:      郭文龙
 * Date:        2023/3/30 0:14
 * Email:       guowenlong20000@sina.com
 */
class TokenInterceptor(private val apiKeys: List<String>) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .removeHeader("Content-Type")
            .addHeader("Content-type", "application/json")
            .addHeader("Authorization", "Bearer ${apiKeys[RandomUtil.randomInt(0, apiKeys.size - 1)]}")
            .build()
        return chain.proceed(request)
    }
}