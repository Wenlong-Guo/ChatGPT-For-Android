package io.github.guowenlong.chatgptforandroid.chat.image

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgpt.model.request.ImageGenerationRequest
import io.github.guowenlong.chatgptforandroid.chat.ChatGPTViewModel
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.completion.adapter.ChatUserViewBinder
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityImageCreateBinding
import io.github.guowenlong.chatgptforandroid.chat.image.adapter.ImageChatGPTViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.base.Status
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

/**
 * Description: 图片生成页面
 * Author:      郭文龙
 * Date:        2023/4/10 2:21
 * Email:       guowenlong20000@sina.com
 */
class ImageCreateActivity(override val layoutId: Int = R.layout.activity_image_create) :
    BaseActivity<ActivityImageCreateBinding>() {

    private val viewModel by viewModel<ChatGPTViewModel>()

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    private val adapter by lazy { MultiTypeAdapter() }

    private val chatGPTViewBinder by lazy { ImageChatGPTViewBinder() }
    private val chatUserViewBinder by lazy { ChatUserViewBinder() }

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@ImageCreateActivity,
            resources.getColor(io.github.guowenlong.chatgptforandroid.common.R.color.status_bar)
        )
        val layoutManager =  binding.rvContent.layoutManager as LinearLayoutManager

        binding.rvContent.itemAnimator = null

        viewModel.insertUserChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }
        viewModel.insertImageGPTChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }

        viewModel.statusLiveData.observe(this) {
            when (it) {
                is Status.Loading -> {
                    binding.tvDesc.text = "正在生成..."
                }
                is Status.Completed -> {
                    binding.tvDesc.text = ""
                }
                is Status.Error -> {
                    Toast.makeText(
                        this@ImageCreateActivity,
                        it.exception.message,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> {}
            }
        }
        layoutManager.stackFromEnd = true
        binding.rvContent.setItemViewCacheSize(2)
        adapter.register(chatGPTViewBinder)
        adapter.register(chatUserViewBinder)
        adapter.items = viewModel.data
        binding.rvContent.adapter = adapter
    }

    override fun bind() {
        binding.btnSend.setOnClickListener {
            viewModel.createImage(
                ImageGenerationRequest(
                    prompt = binding.etContent.text.toString(),
                    n = 4
                )
            )
        }
    }
}