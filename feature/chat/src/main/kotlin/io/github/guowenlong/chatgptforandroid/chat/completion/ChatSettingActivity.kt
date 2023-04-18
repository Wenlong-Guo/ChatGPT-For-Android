package io.github.guowenlong.chatgptforandroid.chat.completion

import android.os.Bundle
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityChatSettingBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.java.KoinJavaComponent

/**
 * Description: 设置页面
 * Author:      郭文龙
 * Date:        2023/3/31 21:44
 * Email:       guowenlong20000@sina.com
 */
class ChatSettingActivity(override val layoutId: Int = R.layout.activity_chat_setting) :
    BaseActivity<ActivityChatSettingBinding>() {

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@ChatSettingActivity,
            ContextCompat.getColor(
                this@ChatSettingActivity,
                io.github.guowenlong.chatgptforandroid.common.R.color.status_bar
            )
        )
        binding.apply {
            sbTemperature.progress = (sp.chatTemperature * 10).toInt()
            sbTopP.progress = (sp.chatTopP * 10).toInt()
            sbMaxToken.progress = sp.chatMaxToken
            sbFrequencyPenalty.progress = (sp.chatFrequencyPenalty * 10 + 20).toInt()
            sbPresencePenalty.progress = (sp.chatPresencePenalty * 10 + 20).toInt()
            sbContext.progress = sp.chatContextCount
        }
    }

    override fun bind() {
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
        binding.sbTopP.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress / 10.0F
                binding.tvCurrentTopP.text = "$value"
                sp.chatTopP = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.sbMaxToken.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvCurrentMaxToken.text = "$progress"
                sp.chatMaxToken = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.sbFrequencyPenalty.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress - 20) / 10.0F
                binding.tvCurrentFrequencyPenalty.text = "$value"
                sp.chatFrequencyPenalty = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.sbPresencePenalty.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress - 20) / 10.0F
                binding.tvCurrentPresencePenalty.text = "$value"
                sp.chatPresencePenalty = value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.sbContext.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvCurrentContext.text = "$progress"
                sp.chatContextCount = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}