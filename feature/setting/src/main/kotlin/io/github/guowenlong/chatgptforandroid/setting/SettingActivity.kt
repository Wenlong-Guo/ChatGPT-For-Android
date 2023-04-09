package io.github.guowenlong.chatgptforandroid.setting

import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import io.github.guowenlong.SingleChatGPT
import io.github.guowenlong.chatgpt.ChatGPT
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import io.github.guowenlong.chatgptforandroid.setting.databinding.ActivitySettingsBinding
import org.koin.java.KoinJavaComponent
import java.net.Proxy

/**
 * Description: 设置页
 * Author:      郭文龙
 * Date:        2023/4/7 0:24
 * Email:       guowenlong20000@sina.com
 */
class SettingsActivity(override val layoutId: Int = R.layout.activity_settings) :
    BaseActivity<ActivitySettingsBinding>() {

    private val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    override fun init(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(
            this@SettingsActivity,
            resources.getColor(io.github.guowenlong.chatgptforandroid.common.R.color.card_background)
        )
        when (sp.proxyType) {
            0 -> binding.rbNull.isChecked = true
            1 -> binding.rbHttp.isChecked = true
            2 -> binding.rbSocks.isChecked = true
            3 -> binding.rbDirect.isChecked = true
        }
        binding.apply {
            etProxyHost.setText(sp.proxyHost)
            etProxyPort.setText(sp.proxyPort.toString())
            etApiKey.setText(sp.openAPIKey)
        }
    }

    override fun bind() {
        binding.apply {
            rgProxyType.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.rb_null -> sp.proxyType = 0
                    R.id.rb_http -> sp.proxyType = 1
                    R.id.rb_socks -> sp.proxyType = 2
                    R.id.rb_direct -> sp.proxyType = 3
                }
            }
            etProxyHost.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sp.proxyHost = etProxyHost.text.toString()
                }
            }
            etProxyPort.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sp.proxyPort = etProxyPort.text.toString().toInt()
                }
            }
            etApiKey.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sp.openAPIKey = etApiKey.text.toString()
                }
            }
            etApiHost.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    sp.openAPIHost = etApiHost.text.toString()
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
        sp.proxyHost = binding.etProxyHost.text.toString()
        sp.proxyPort = binding.etProxyPort.text.toString().toInt()
        sp.openAPIKey = binding.etApiKey.text.toString()
        sp.openAPIHost = binding.etApiHost.text.toString()
        SingleChatGPT.instance = ChatGPT.Builder()
            .setReadTimeout(2 * 60_000)
            .setWriteTimeout(2 * 60_000)
            .setConnectTimeout(2 * 60_000)
            .setBaseUrl(sp.openAPIHost)
            .setApiKeys(sp.openAPIKey)
            .also { builder ->
                val type = when (sp.proxyType) {
                    1 -> Proxy.Type.HTTP
                    2 -> Proxy.Type.SOCKS
                    3 -> Proxy.Type.DIRECT
                    else -> null
                }
                type?.let {
                    builder.setProxyUrl(type, sp.proxyHost, sp.proxyPort)
                }
            }
            .setApiKeys(sp.openAPIKey)
            .setLogEnable(true)
            .build()
    }
}