package io.github.guowenlong.chatgptforandroid.chat.image.adapter

import androidx.recyclerview.widget.GridLayoutManager
import io.github.guowenlong.chatgpt.model.response.ImageGeneration
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemImageChatgptBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Description: 图片item的ViewBinder
 * Author:      郭文龙
 * Date:        2023/4/10 2:09
 * Email:       guowenlong20000@sina.com
 */
class ImageChatGPTViewBinder(override val itemLayoutId: Int = R.layout.item_image_chatgpt) :
    BaseBindingViewBinder<ImageGeneration, ItemImageChatgptBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemImageChatgptBinding>,
        itemData: ImageGeneration,
        binding: ItemImageChatgptBinding
    ) {
        binding.tvTime.text =
            SimpleDateFormat(
                "MM-dd HH:mm:ss",
                Locale.getDefault()
            ).format(Date(System.currentTimeMillis()))
        val layoutManager = object : GridLayoutManager(binding.root.context, 1){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.rvImage.layoutManager = layoutManager
        val adapter = ImageAdapter()
        adapter.data = itemData.data.toMutableList()
        binding.rvImage.adapter = adapter
    }
}