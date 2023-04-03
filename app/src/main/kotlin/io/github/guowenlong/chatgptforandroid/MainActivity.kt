package io.github.guowenlong.chatgptforandroid

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgptforandroid.adapter.ChatGPTViewBinder
import io.github.guowenlong.chatgptforandroid.adapter.ChatUserViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModel<MainViewModel>()

    private val chatGPTViewBinder by lazy { ChatGPTViewBinder() }
    private val chatUserViewBinder by lazy { ChatUserViewBinder() }
    private val adapter by lazy { MultiTypeAdapter() }

    override fun init(savedInstanceState: Bundle?) {
        val layoutManager = (binding.rvContent.layoutManager as LinearLayoutManager)
        binding.rvContent.itemAnimator = null

        viewModel.insertUserChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
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
        layoutManager.stackFromEnd = true
        binding.rvContent.setItemViewCacheSize(2)
        adapter.register(chatGPTViewBinder)
        adapter.register(chatUserViewBinder)
        adapter.items = viewModel.data
        binding.rvContent.adapter = adapter
    }

    override fun bind() {
        binding.button.setOnClickListener {
            viewModel.completions()
        }
        binding.btnSend.setOnClickListener {
            viewModel.completionStream(
                CompletionRequest(
                    messages = listOf(
                        CompletionRequest.Message(
                            content = binding.etContent.text.toString()
                        )
                    )
                )
            )
            binding.etContent.setText("")
        }
    }
}