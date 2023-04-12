package io.github.guowenlong.chatgptforandroid.chat.translation.adapter

import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemTranslationUserBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import io.github.guowenlong.chatgptforandroid.model.UserTranslation
import java.text.SimpleDateFormat
import java.util.*

/**
 * Description: 用户翻译item
 * Author:      郭文龙
 * Date:        2023/4/12 18:17
 * Email:       guowenlong20000@sina.com
 */
class TranslationUserViewBinder(override val itemLayoutId: Int = R.layout.item_translation_user) :
    BaseBindingViewBinder<UserTranslation, ItemTranslationUserBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemTranslationUserBinding>,
        itemData: UserTranslation,
        binding: ItemTranslationUserBinding
    ) {
        binding.tvTime.text =
            SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault()).format(Date(itemData.time))
        binding.tvFile.text = itemData.filePath
    }
}