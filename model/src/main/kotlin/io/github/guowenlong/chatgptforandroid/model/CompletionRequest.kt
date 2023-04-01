package io.github.guowenlong.chatgptforandroid.model

/**
 * Description: 聊天请求参数
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
data class CompletionRequest(
    val messages: List<Message>,
    val logprobs: Int? = null,
    val max_tokens: Int = 3500,
    val model: String = "gpt-3.5-turbo-0301",
    val n: Int = 1,
    val prompt: String? = null,
    val stop: String? = null,
    val stream: Boolean = false,
    val temperature: Int = 1,
    val top_p: Int = 1
) {
    data class Message(
        val role: String = "user",
        val content: String
    )
}