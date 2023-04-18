package io.github.guowenlong.chatgptforandroid.chat.completion

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgptforandroid.chat.ChatGPTViewModel
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.completion.adapter.ChatGPTViewBinder
import io.github.guowenlong.chatgptforandroid.chat.completion.adapter.ChatUserViewBinder
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityChatBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.base.Status
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent

/**
 * Description: 普通聊天页面
 * Author:      郭文龙
 * Date:        2023/4/7 1:38
 * Email:       guowenlong20000@sina.com
 */
class ChatActivity(override val layoutId: Int = R.layout.activity_chat) :
    BaseActivity<ActivityChatBinding>() {

    private val viewModel by viewModel<ChatGPTViewModel>()

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    private val adapter by lazy { MultiTypeAdapter() }

    private val chatGPTViewBinder by lazy { ChatGPTViewBinder() }
    private val chatUserViewBinder by lazy { ChatUserViewBinder() }

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@ChatActivity,
            ContextCompat.getColor(
                this@ChatActivity,
                io.github.guowenlong.chatgptforandroid.common.R.color.status_bar
            )
        )
        val layoutManager = (binding.rvContent.layoutManager as LinearLayoutManager)
        binding.rvContent.itemAnimator = null

        viewModel.insertUserChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }
        viewModel.insertGPTChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
        }
        viewModel.updateLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemChanged(it)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }
        viewModel.statusLiveData.observe(this) {
            when (it) {
                is Status.Loading -> {
                    binding.tvDesc.text =
                        getString(io.github.guowenlong.chatgptforandroid.common.R.string.inputting)
                }
                is Status.Completed -> {
                    binding.tvDesc.text = ""
                }
                is Status.Error -> {
                    Toast.makeText(this@ChatActivity, it.exception.message, Toast.LENGTH_SHORT)
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
        binding.tvBack.setOnClickListener {
            finish()
        }
        binding.ivSetting.setOnClickListener {
            startActivity(Intent(this@ChatActivity, ChatSettingActivity::class.java))
        }
        binding.btnSend.setOnClickListener {
            viewModel.completionStreamWithContext(
                CompletionRequest(
                    messages = listOf(
                        CompletionRequest.Message(
                            content = binding.etContent.text.toString()
                        )
                    ),
                    temperature = sp.chatTemperature.toDouble(),
                    topP = sp.chatTopP.toDouble(),
                    maxTokens = sp.chatMaxToken,
                    presencePenalty = sp.chatPresencePenalty.toDouble(),
                    frequencyPenalty = sp.chatFrequencyPenalty.toDouble(),
                ),
                count = sp.chatContextCount
            )
            binding.etContent.setText("")
        }
    }
}