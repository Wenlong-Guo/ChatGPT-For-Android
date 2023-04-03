package io.github.guowenlong.chatgptforandroid.adapter

import io.github.guowenlong.chatgpt.model.response.CompletionStream
import io.github.guowenlong.chatgptforandroid.R
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import io.github.guowenlong.chatgptforandroid.databinding.ItemChatgptBinding

/**
 * Description: ChatGPT的回复item
 * Author:      郭文龙
 * Date:        2023/4/3 0:52
 * Email:       guowenlong20000@sina.com
 */
class ChatGPTViewBinder(override val itemLayoutId: Int = R.layout.item_chatgpt) :
    BaseBindingViewBinder<CompletionStream, ItemChatgptBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemChatgptBinding>,
        itemData: CompletionStream,
        binding: ItemChatgptBinding
    ) {
        binding.tvContent.text=itemData.choices[0].delta.content
    }

}