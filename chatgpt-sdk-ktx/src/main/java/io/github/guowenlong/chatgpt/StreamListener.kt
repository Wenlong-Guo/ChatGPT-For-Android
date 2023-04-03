package io.github.guowenlong.chatgpt

import io.github.guowenlong.chatgpt.model.response.CompletionStream

/**
 * Description: 流式响应监听器
 * Author:      郭文龙
 * Date:        2023/4/2 22:30
 * Email:       guowenlong20000@sina.com
 */
interface StreamListener {
    /**
     * 开始
     */
    fun onStart()

    /**
     * 流式响应发送第一条（第一条content为空数据）
     */
    fun onStreamPre(completionStream: CompletionStream)

    /**
     * 流式响应进行中
     * @param completionStream 内容
     */
    fun onStream(completionStream: CompletionStream)

    /**
     * 完成
     */
    fun onCompleted()

    /**
     * 异常
     */
    fun onError(exception:Exception)
}