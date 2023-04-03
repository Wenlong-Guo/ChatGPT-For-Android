package io.github.guowenlong.chatgpt

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.squareup.moshi.Moshi
import io.github.guowenlong.chatgpt.api.OpenAIApi
import io.github.guowenlong.chatgpt.interceptor.TokenInterceptor
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.response.Completion
import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgpt.model.response.Model
import io.github.guowenlong.chatgpt.utils.RandomUtil
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

/**
 * Description: 自己封装的ChatGPT的SDK
 * Author:      郭文龙
 * Date:        2023/4/2 14:54
 * Email:       guowenlong20000@sina.com
 */
class ChatGPT private constructor() {

    private lateinit var client: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var baseUrl: String
    private var apiKeys: List<String> = listOf()

    private lateinit var api: OpenAIApi
    private var proxyType: Proxy.Type? = null
    private var proxyUrl: String? = null
    private var proxyPort: Int? = null
    private var readTimeout: Int = 60_000
    private var connectTimeout: Int = 60_000

    constructor(builder: Builder) : this() {
        client = initOkHttp(
            builder.readTimeout,
            builder.writeTimeout,
            builder.connectTimeout,
            builder.isLogEnable,
            builder.apiKeys
        )
        retrofit = initRetrofit(builder.baseUrl)
        api = initApi()
        proxyType = builder.proxyType
        proxyPort = builder.proxyPort
        proxyUrl = builder.proxyUrl
        baseUrl = builder.baseUrl
        readTimeout = builder.readTimeout
        connectTimeout = builder.connectTimeout
        apiKeys = builder.apiKeys
    }

    suspend fun getModels(): Model {
        return api.getModels()
    }

    suspend fun completions(completionRequest: CompletionRequest): Completion {
        completionRequest.stream = false
        return api.completions(completionRequest)
    }

    fun completionsByStream(
        completionRequest: CompletionRequest,
        listener: StreamListener
    ) {
        var proxy: Proxy? = null
        if (proxyType != null && proxyUrl?.isNotBlank() == true && proxyPort != null) {
            proxy = Proxy(proxyType, InetSocketAddress(proxyUrl, proxyPort!!))
        }

        completionRequest.stream = true

        val url = URL("${baseUrl}v1/chat/completions")
        val connection = if (proxy != null) {
            url.openConnection(proxy) as HttpsURLConnection
        } else {
            url.openConnection() as HttpsURLConnection
        }

        var isCreate = false
        try {
            val json = Moshi.Builder().build()
                .adapter(CompletionRequest::class.java)
                .toJson(completionRequest)

            connection.requestMethod = "POST"
            connection.setRequestProperty("Authorization", "Bearer ${apiKeys[RandomUtil.randomInt(0, apiKeys.size - 1)]}")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "text/event-stream")

            connection.connectTimeout = connectTimeout
            connection.readTimeout = readTimeout

            connection.doOutput = true

            val outputStream = BufferedOutputStream(connection.outputStream)

            outputStream.write(json.toByteArray())
            outputStream.flush()
            listener.onStart()
            val input = BufferedInputStream(connection.inputStream)
            val reader = BufferedReader(InputStreamReader(input))
            while (true) {
                val line = reader.readLine() ?: break
                if (line.trim().isNotBlank()) {
                    val strings = line.split("data: ")
                    if (strings[1] == "[DONE]") return
                    val completionStream = Moshi.Builder().build()
                        .adapter(CompletionStream::class.java)
                        .fromJson(strings[1])
                    completionStream?.let {
                        if (it.choices[0].delta.content.isBlank() && !isCreate) {
                            listener.onStreamPre(it)
                            isCreate = true
                        } else {
                            listener.onStream(it)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            listener.onError(e)
        } finally {
            connection.disconnect()
            listener.onCompleted()
        }
    }


    private fun initOkHttp(
        readTimeout: Int,
        writeTimeout: Int,
        connectTimeout: Int,
        isLogEnable: Boolean,
        apiKeys: List<String>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(writeTimeout.toLong(), TimeUnit.SECONDS)
            .connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor(apiKeys))
            .also {
                if (isLogEnable) {
                    val loggingInterceptor = LoggingInterceptor.Builder()
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .build()
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .retryOnConnectionFailure(true)
            .build()
    }

    private fun initRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun initApi(): OpenAIApi {
        return retrofit.create(OpenAIApi::class.java)
    }

    class Builder {
        var baseUrl: String = ""
        var readTimeout: Int = 2 * 60_000
        var writeTimeout: Int = 2 * 60_000
        var connectTimeout: Int = 2 * 60_000
        var isLogEnable: Boolean = false
        var proxyType: Proxy.Type? = null
        var proxyUrl: String? = null
        var proxyPort: Int? = null
        var apiKeys: List<String> = listOf()

        fun setBaseUrl(apiUrl: String): Builder {
            this.baseUrl = apiUrl
            return this
        }

        fun setReadTimeout(readTimeout: Int): Builder {
            this.readTimeout = readTimeout
            return this
        }

        fun setWriteTimeout(writeTimeout: Int): Builder {
            this.writeTimeout = writeTimeout
            return this
        }

        fun setConnectTimeout(connectTimeout: Int): Builder {
            this.connectTimeout = connectTimeout
            return this
        }

        fun setLogEnable(isLogEnable: Boolean): Builder {
            this.isLogEnable = isLogEnable
            return this
        }

        fun setProxyUrl(proxyType: Proxy.Type, proxyUrl: String, proxyPort: Int): Builder {
            this.proxyType = proxyType
            this.proxyPort = proxyPort
            this.proxyUrl = proxyUrl
            return this
        }

        fun setApiKeys(vararg apiKeys: String): Builder {
            this.apiKeys = apiKeys.toList()
            return this
        }

        fun build(): ChatGPT {
            if (apiKeys.isEmpty()) throw IllegalArgumentException("api key must be not null")
            return ChatGPT(this)
        }
    }
}