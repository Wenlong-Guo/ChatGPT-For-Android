package io.github.guowenlong.chatgptforandroid.chat.image.adapter

import android.widget.Toast
import com.bumptech.glide.Glide
import io.github.guowenlong.chatgpt.model.response.ImageGeneration
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ItemImageBinding
import io.github.guowenlong.chatgptforandroid.common.DownloadUtils
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingAdapter
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingViewHolder

/**
 * Description: 图片的item
 * Author:      郭文龙
 * Date:        2023/4/10 2:12
 * Email:       guowenlong20000@sina.com
 */
class ImageAdapter(override val itemLayoutId: Int = R.layout.item_image) :
    BaseBindingAdapter<ImageGeneration.Data, ItemImageBinding>() {

    override fun bindData(
        holder: BaseBindingViewHolder<ItemImageBinding>,
        position: Int,
        itemData: ImageGeneration.Data,
        binding: ItemImageBinding
    ) {
        Glide.with(binding.root.context).load(itemData.url).into(binding.ivImage)
        binding.ivImage.setOnLongClickListener {
            DownloadUtils.downloadPic(binding.root.context, itemData.url, {
                Toast.makeText(
                    binding.root.context,
                    binding.root.resources.getString(
                        io.github.guowenlong.chatgptforandroid.common.R.string.the_picture_is_saved_to,
                        it
                    ),
                    Toast.LENGTH_LONG
                ).show()
            }, {
                Toast.makeText(
                    binding.root.context,
                    binding.root.resources.getString(io.github.guowenlong.chatgptforandroid.common.R.string.download_failed),
                    Toast.LENGTH_SHORT
                ).show()
            })
            return@setOnLongClickListener true
        }
    }
}