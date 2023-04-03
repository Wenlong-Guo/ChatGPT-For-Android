package io.github.guowenlong.chatgptforandroid.adapter

import io.github.guowenlong.chatgptforandroid.R
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import io.github.guowenlong.chatgptforandroid.databinding.ItemUserBinding

/**
 * Description: 用户发出的提问item
 * Author:      郭文龙
 * Date:        2023/4/3 0:54
 * Email:       guowenlong20000@sina.com
 */
class ChatUserViewBinder(override val itemLayoutId: Int = R.layout.item_user) :
    BaseBindingViewBinder<String, ItemUserBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemUserBinding>,
        itemData: String,
        binding: ItemUserBinding
    ) {
        binding.tvContent.text = itemData
    }

}