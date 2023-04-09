package io.github.guowenlong.chatgptforandroid.chat.completion.adapter

import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemChatChatgptBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Description: ChatGPT的回复item
 * Author:      郭文龙
 * Date:        2023/4/3 0:52
 * Email:       guowenlong20000@sina.com
 */
class ChatGPTViewBinder(override val itemLayoutId: Int = R.layout.item_chat_chatgpt) :
    BaseBindingViewBinder<CompletionStream, ItemChatChatgptBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemChatChatgptBinding>,
        itemData: CompletionStream,
        binding: ItemChatChatgptBinding
    ) {
        binding.tvContent.text = itemData.choices[0].delta.content
        binding.tvTime.text =
            SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault()).format(
                Date(
                    itemData.time ?: System.currentTimeMillis()
                )
            )
    }
}