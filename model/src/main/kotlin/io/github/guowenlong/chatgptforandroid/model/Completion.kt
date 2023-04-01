package io.github.guowenlong.chatgptforandroid.model

/**
 * Description: 聊天实体类
 * Author:      郭文龙
 * Date:        2023/4/1 2:02
 * Email:       guowenlong20000@sina.com
 */
data class Completion(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
){
    data class Choice(
        val finish_reason: String,
        val index: Int,
        val logprobs: Any,
        val text: String
    )

    data class Usage(
        val completion_tokens: Int,
        val prompt_tokens: Int,
        val total_tokens: Int
    )
}