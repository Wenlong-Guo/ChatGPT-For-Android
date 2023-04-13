package io.github.guowenlong.chatgptforandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.chatgptforandroid.chat.completion.ChatActivity
import io.github.guowenlong.chatgptforandroid.chat.image.ImageCreateActivity
import io.github.guowenlong.chatgptforandroid.chat.translation.TranslationActivity
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.databinding.ActivityMainBinding
import io.github.guowenlong.chatgptforandroid.setting.SettingsActivity
import org.koin.java.KoinJavaComponent

/**
 * Description: 首页
 * Author:      郭文龙
 * Date:        2023/3/31 2:38
 * Email:       guowenlong20000@sina.com
 */
class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@MainActivity,
            ContextCompat.getColor(
                this@MainActivity,
                io.github.guowenlong.chatgptforandroid.common.R.color.card_background
            )
        )

        binding.cvChat.setOnClickListener {
            if (checkApiKeyNull()) return@setOnClickListener
            startActivity(Intent(this@MainActivity, ChatActivity::class.java))
        }
        binding.cvCreateImage.setOnClickListener {
            if (checkApiKeyNull()) return@setOnClickListener
            XXPermissions.with(this).permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request { _, allGranted ->
                    if (allGranted) {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                ImageCreateActivity::class.java
                            )
                        )
                    }
                }
        }
        binding.cvVoice.setOnClickListener {
            if (checkApiKeyNull()) return@setOnClickListener
            XXPermissions.with(this).permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request { _, allGranted ->
                    if (allGranted) {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                TranslationActivity::class.java
                            )
                        )
                    }
                }
        }
        binding.ivSetting.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }
    }

    private fun checkApiKeyNull(): Boolean {
        return if (sp.openAPIKey.isBlank()) {
            Toast.makeText(this@MainActivity, getString(io.github.guowenlong.chatgptforandroid.common.R.string.please_input_api_key), Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            true
        } else {
            false
        }
    }

    override fun bind() {

    }
}