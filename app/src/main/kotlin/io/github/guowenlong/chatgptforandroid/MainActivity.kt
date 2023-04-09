package io.github.guowenlong.chatgptforandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgptforandroid.chat.completion.ChatActivity
import io.github.guowenlong.chatgptforandroid.chat.image.ImageCreateActivity
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.databinding.ActivityMainBinding
import io.github.guowenlong.chatgptforandroid.setting.SettingsActivity
import org.koin.java.KoinJavaComponent
import java.io.File
import java.io.FileOutputStream

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@MainActivity,
            resources.getColor(io.github.guowenlong.chatgptforandroid.common.R.color.card_background)
        )

        binding.cvChat.setOnClickListener {
            if (checkApiKeyNull()) return@setOnClickListener
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
        binding.cvCreateImage.setOnClickListener {
            if (checkApiKeyNull()) return@setOnClickListener
            startActivity(Intent(this@MainActivity, ImageCreateActivity::class.java))
        }
        binding.ivSetting.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }

    private fun checkApiKeyNull(): Boolean {
        return if (sp.openAPIKey.isBlank()) {
            Toast.makeText(this@MainActivity, "请先设置API Key", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            true
        } else {
            false
        }
    }

    override fun bind() {
//        binding.button.setOnClickListener {
//            copyAssetFileToCache(this@ChatActivity, "ic_edit_image.png")
//            viewModel.variationImage(File(cacheDir.absolutePath, "ic_edit_image.png"))
//            viewModel.embeddings()
//
//            copyAssetFileToCache(this@ChatActivity, "shinian.mp3")
//            viewModel.translation(File(cacheDir.absolutePath, "shinian.mp3"))
//
//            return@setOnClickListener
//        }
//        binding.btnSend.setOnClickListener {
//            viewModel.completionStream(
//                CompletionRequest(
//                    messages = listOf(
//                        CompletionRequest.Message(
//                            content = binding.etContent.text.toString()
//                        )
//                    )
//                )
//            )
//            binding.etContent.setText("")
//        }
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