package io.github.guowenlong.chatgptforandroid.chat.translation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.jaeger.library.StatusBarUtil
import com.zlylib.fileselectorlib.FileSelector
import com.zlylib.fileselectorlib.bean.EssFile
import com.zlylib.fileselectorlib.utils.Const
import io.github.guowenlong.chatgptforandroid.chat.ChatGPTViewModel
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityTranslationBinding
import io.github.guowenlong.chatgptforandroid.chat.translation.adapter.TranslationChatGPTViewBinder
import io.github.guowenlong.chatgptforandroid.chat.translation.adapter.TranslationUserViewBinder
import io.github.guowenlong.chatgptforandroid.common.base.BaseBindingActivity
import io.github.guowenlong.chatgptforandroid.common.base.Status
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.common.ext.logE
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent
import java.io.File


/**
 * Description:
 * Author:      郭文龙
 * Date:        2023/4/10 23:28
 * Email:       guowenlong20000@sina.com
 */
class TranslationActivity(override val layoutId: Int = R.layout.activity_translation) :
    BaseBindingActivity<ActivityTranslationBinding>() {

    private val viewModel by viewModel<ChatGPTViewModel>()

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    private val adapter by lazy { MultiTypeAdapter() }

    private val chatGPTViewBinder by lazy { TranslationChatGPTViewBinder() }
    private val chatUserViewBinder by lazy { TranslationUserViewBinder() }

    private var filePath: String? = null

    override fun init(savedInstanceState: Bundle?) {

        StatusBarUtil.setColor(
            this@TranslationActivity,
            resources.getColor(io.github.guowenlong.chatgptforandroid.common.R.color.status_bar)
        )
        val layoutManager = binding.rvContent.layoutManager as LinearLayoutManager

        binding.rvContent.itemAnimator = null

        viewModel.insertUserChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }
        viewModel.insertTranslationGPTChatLiveData.observe(this) {
            adapter.items = viewModel.data
            adapter.notifyItemInserted(adapter.items.size - 1)
            binding.rvContent.postDelayed({
                layoutManager.scrollToPositionWithOffset(adapter.items.size - 1, Int.MIN_VALUE)
            }, 200)
        }

        viewModel.statusLiveData.observe(this) {
            when (it) {
                is Status.Loading -> {
                    binding.tvDesc.text = getString(io.github.guowenlong.chatgptforandroid.common.R.string.translating)
                }
                is Status.Completed -> {
                    binding.tvDesc.text = ""
                }
                is Status.Error -> {
                    Toast.makeText(
                        this@TranslationActivity,
                        it.exception.message,
                        Toast.LENGTH_SHORT
                    ).show()
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
        binding.tvFile.setOnClickListener {
            FileSelector.from(this)
                .setMaxCount(1)
                .setFileTypes(
                    "mp3",
                    "mp4",
                    "mpeg",
                    "m4a",
                    "wav",
                    "webm",
                ) //设置文件类型
                .setSortType(FileSelector.BY_TIME_ASC) //设置时间排序
                .requestCode(1) //设置返回码
                .setTargetPath("/storage/emulated/0/") //设置默认目录
                .start()
        }
        binding.btnSend.setOnClickListener {
            if (binding.tvFile.text == getString(io.github.guowenlong.chatgptforandroid.common.R.string.select_file)) {
                Toast.makeText(this, getString(io.github.guowenlong.chatgptforandroid.common.R.string.please_select_a_file), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.translation(
                file = File(filePath ?: return@setOnClickListener),
                temperature = sp.translationTemperature.toDouble(),
                language = sp.translationLanguage,
            )
            binding.tvFile.text = getString(io.github.guowenlong.chatgptforandroid.common.R.string.select_file)
        }
        binding.ivSetting.setOnClickListener {
            startActivity(Intent(this@TranslationActivity, TranslationSettingActivity::class.java))
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (data != null) {
                val essFileList =
                    data.getParcelableArrayListExtra<EssFile>(Const.EXTRA_RESULT_SELECTION)
                logE(essFileList.toString())
                binding.tvFile.text = essFileList?.get(0)?.absolutePath ?: ""
                filePath = essFileList?.get(0)?.absolutePath
            }
        }
    }
}