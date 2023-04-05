package io.github.guowenlong.chatgptforandroid

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import io.github.guowenlong.chatgpt.model.request.CompletionRequest
import io.github.guowenlong.chatgptforandroid.adapter.ChatGPTViewBinder
import io.github.guowenlong.chatgptforandroid.adapter.ChatUserViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.databinding.ActivityChatBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class ChatActivity(override val layoutId: Int = R.layout.activity_chat) :
    BaseActivity<ActivityChatBinding>() {

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
            copyAssetFileToCache(this@ChatActivity, "ic_edit_image.png")
            viewModel.editImage(File(cacheDir.absolutePath, "ic_edit_image.png"))
            return@setOnClickListener
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

    private fun copyAssetFileToCache(context: Context, fileName: String) {
        val assetManager = context.assets
        val inputStream = assetManager.open(fileName)
        val cacheDir = context.cacheDir
        val outputFile = File(cacheDir, fileName)
        val outputStream = FileOutputStream(outputFile)
        val buffer = ByteArray(1024)
        var length = inputStream.read(buffer)
        while (length > 0) {
            outputStream.write(buffer, 0, length)
            length = inputStream.read(buffer)
        }
        inputStream.close()
        outputStream.close()
    }
}