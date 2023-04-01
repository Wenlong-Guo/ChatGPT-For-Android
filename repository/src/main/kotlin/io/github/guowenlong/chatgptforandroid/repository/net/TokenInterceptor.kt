package io.github.guowenlong.chatgptforandroid.repository.net

import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.java.KoinJavaComponent

/**
 * Description: OpenApiKey拦截器
 * Author:      郭文龙
 * Date:        2023/3/30 0:14
 * Email:       guowenlong20000@sina.com
 */
class TokenInterceptor : Interceptor {

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .removeHeader("Content-Type")
            .addHeader("Content-type", "application/json")
            .also {
                if (!sp.openAPIKey.isNullOrBlank()) it.addHeader(
                    "Authorization",
                    "Bearer ${sp.openAPIKey}"
                )
            }
            .build()
        return chain.proceed(request)
    }
}