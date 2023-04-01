package io.github.guowenlong.chatgptforandroid.repository.di

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.repository.Config
import io.github.guowenlong.chatgptforandroid.repository.OpenAIRepository
import io.github.guowenlong.chatgptforandroid.repository.net.OpenAIApi
import io.github.guowenlong.chatgptforandroid.repository.net.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Description: Repository模块的依赖注入
 * Author:      郭文龙
 * Date:        2023/3/30 0:12
 * Email:       guowenlong20000@sina.com
 */


val loggingInterceptor = LoggingInterceptor.Builder()
    .setLevel(Level.BASIC)
    .log(Platform.INFO)
    .request("Request")
    .response("Response")
    .build()

val tokenInterceptor = TokenInterceptor()

val repositoryModule = module {
    //sp init
    single { SpProperty() }

    //okhttp init
    single {
        OkHttpClient.Builder()
            .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    //retrofit init
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(Config.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    single { (get() as Retrofit).create(OpenAIApi::class.java) }
    single { OpenAIRepository(get()) }
}
