package io.github.guowenlong.chatgptforandroid.repository

import io.github.guowenlong.SingleChatGPT
import io.github.guowenlong.chatgpt.StreamListener
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgpt.model.request.EditRequest
import io.github.guowenlong.chatgpt.model.request.EmbeddingsRequest
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgpt.model.response.ImageGeneration
import io.github.guowenlong.chatgptforandroid.common.base.BaseRepository
import java.io.File


/**
 * Description: OpenAI的仓库
 * Author:      郭文龙
 * Date:        2023/3/31 1:26
 * Email:       guowenlong20000@sina.com
 */
class ChatGPTRepository : BaseRepository() {

    suspend fun getModels() = request {
        SingleChatGPT.instance.getModels()
    }

    suspend fun getCompletions(completionRequest: CompletionRequest) = request {
        SingleChatGPT.instance.completions(completionRequest)
    }

    suspend fun getCompletionsByString(
        completionRequest: CompletionRequest,
        listener: StreamListener
    ) = request {
        SingleChatGPT.instance.completionsByStream(completionRequest, listener)
    }

    suspend fun getEdits(editRequest: EditRequest) = request {
        SingleChatGPT.instance.edits(editRequest)
    }

    suspend fun generationImage(imageGenerationRequest: ImageGenerationRequest) = request {
        SingleChatGPT.instance.generationImage(imageGenerationRequest)
    }

    suspend fun editImage(
        image: File,
        prompt: String,
        mask: File? = null,
        n: Int? = null,
        size: String? = null,
        response_format: String? = null,
        user: String? = null
    ) = request {
        SingleChatGPT.instance.editImage(image, prompt, mask, n, size, response_format, user)
    }

    suspend fun variationImage(
        image: File,
        n: Int? = null,
        size: String? = null,
        response_format: String? = null,
        user: String? = null
    ): ImageGeneration {
        return SingleChatGPT.instance.variationImage(image, n, size, response_format, user)
    }

    suspend fun getEmbeddings(embeddingsRequest: EmbeddingsRequest) = request {
        SingleChatGPT.instance.embeddings(embeddingsRequest)
    }

    suspend fun translation(
        file: File,
        model: String,
        prompt: String? = null,
        response_format: String? = null,
        temperature: Double? = null,
        language: String? = null
    ) = request {
        SingleChatGPT.instance.translation(file, model, prompt, response_format, temperature, language)
    }
}