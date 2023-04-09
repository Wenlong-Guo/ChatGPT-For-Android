package io.github.guowenlong.chatgptforandroid.chat.completion.adapter

import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemChatUserBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import io.github.guowenlong.chatgptforandroid.model.UserChat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Description: 用户发出的提问item
 * Author:      郭文龙
 * Date:        2023/4/3 0:54
 * Email:       guowenlong20000@sina.com
 */
class ChatUserViewBinder(override val itemLayoutId: Int = R.layout.item_chat_user) :
    BaseBindingViewBinder<UserChat, ItemChatUserBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemChatUserBinding>,
        itemData: UserChat,
        binding: ItemChatUserBinding
    ) {
        binding.tvContent.text = itemData.content
        // 将long转换为时间
        binding.tvTime.text =
            SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault()).format(Date(itemData.time))
    }

}