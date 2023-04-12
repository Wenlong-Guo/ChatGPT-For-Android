package io.github.guowenlong.chatgptforandroid.chat.translation

import android.os.Bundle
import android.widget.SeekBar
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityTranslationSettingBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.java.KoinJavaComponent

/**
 * Description: 音频翻译文字设置页面
 * Author:      郭文龙
 * Date:        2023/4/11 2:45
 * Email:       guowenlong20000@sina.com
 */
class TranslationSettingActivity(override val layoutId: Int = R.layout.activity_translation_setting) :
    BaseActivity<ActivityTranslationSettingBinding>() {

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        binding.sbTemperature.progress = (sp.chatTemperature * 10).toInt()
    }

    override fun bind() {
        binding.tvBack.setOnClickListener {
            finish()
        }
        binding.sbTemperature.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress / 10.0F
                binding.tvCurrentTemperature.text = "$value"
                sp.chatTemperature = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onStop() {
        super.onStop()
        sp.translationPrompt = binding.etContent.text.toString()
        sp.translationLanguage = binding.etLanguage.text.toString()
    }
}