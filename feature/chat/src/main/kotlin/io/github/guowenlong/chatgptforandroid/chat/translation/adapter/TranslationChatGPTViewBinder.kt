package io.github.guowenlong.chatgptforandroid.chat.translation.adapter

import io.github.guowenlong.chatgpt.model.response.Translation
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemTranslationChatgptBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder

/**
 * Description: ChatGPT的翻译item
 * Author:      郭文龙
 * Date:        2023/4/12 18:17
 * Email:       guowenlong20000@sina.com
 */
class TranslationChatGPTViewBinder(override val itemLayoutId: Int = R.layout.item_translation_chatgpt) :
    BaseBindingViewBinder<Translation, ItemTranslationChatgptBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemTranslationChatgptBinding>,
        itemData: Translation,
        binding: ItemTranslationChatgptBinding
    ) {
        binding.tvContent.text = itemData.text
    }
}