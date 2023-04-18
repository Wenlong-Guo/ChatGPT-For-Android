package io.github.guowenlong.chatgptforandroid.chat.image

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgptforandroid.chat.R
import io.github.guowenlong.chatgptforandroid.chat.databinding.ActivityImageCreateSettingBinding
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import org.koin.java.KoinJavaComponent

/**
 * Description: 图片创建的设置页面
 * Author:      郭文龙
 * Date:        2023/4/10 17:36
 * Email:       guowenlong20000@sina.com
 */
class ImageCreateSettingActivity(override val layoutId: Int = R.layout.activity_image_create_setting) :
    BaseActivity<ActivityImageCreateSettingBinding>() {

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this,
            ContextCompat.getColor(
                this,
                io.github.guowenlong.chatgptforandroid.common.R.color.status_bar
            )
        )
        binding.apply {
            tvCurrentN.text = sp.imageCreateN.toString()
            sbN.progress = sp.imageCreateN
            when (sp.imageCreateSize) {
                "1024x1024" -> rb1024.isChecked = true
                "512x512" -> rb512.isChecked = true
                "256x256" -> rb256.isChecked = true
            }
            sbN.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: android.widget.SeekBar?, p1: Int, p2: Boolean) {
                    tvCurrentN.text = p1.toString()
                    sp.imageCreateN = p1
                }

                override fun onStartTrackingTouch(p0: android.widget.SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: android.widget.SeekBar?) {
                }
            })
            rb1024.setOnClickListener {
                sp.imageCreateSize = "1024x1024"
            }
            rb512.setOnClickListener {
                sp.imageCreateSize = "512x512"
            }
            rb256.setOnClickListener {
                sp.imageCreateSize = "256x256"
            }
        }
    }

    override fun bind() {
        binding.tvBack.setOnClickListener {
            finish()
        }
    }
}