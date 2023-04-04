package io.github.guowenlong.chatgptforandroid.repository

import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgpt.StreamListener
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.EditRequest
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgptforandroid.common.base.BaseRepository


/**
 * Description: OpenAI的仓库
 * Author:      郭文龙
 * Date:        2023/3/31 1:26
 * Email:       guowenlong20000@sina.com
 */
class OpenAIRepository(private val chatGPT: ChatGPT) : BaseRepository() {

    suspend fun getModels() = request {
        chatGPT.getModels()
    }

    suspend fun getCompletions(completionRequest: CompletionRequest) = request {
        chatGPT.completions(completionRequest)
    }

    suspend fun getCompletionsByString(completionRequest: CompletionRequest,listener: StreamListener) = request {
        chatGPT.completionsByStream(completionRequest,listener)
    }

    suspend fun getEdits(editRequest: EditRequest) = request {
        chatGPT.edits(editRequest)
    }

    suspend fun generationImage(imageGenerationRequest: ImageGenerationRequest) = request {
        chatGPT.generationImage(imageGenerationRequest)
    }
}