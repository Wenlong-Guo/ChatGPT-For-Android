package io.github.guowenlong

import io.github.guowenlong.chatgpt.ChatGPT

/**
 * Description: 保持一个单例的[ChatGPT]
 * Author:      郭文龙
 * Date:        2023/4/8 23:11
 * Email:       guowenlong20000@sina.com
 */
object SingleChatGPT {
    lateinit var instance: ChatGPT
}