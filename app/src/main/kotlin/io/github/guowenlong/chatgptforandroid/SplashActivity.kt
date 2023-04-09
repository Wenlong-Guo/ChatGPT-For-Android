package io.github.guowenlong.chatgptforandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import io.github.guowenlong.chatgptforandroid.common.base.BaseActivity
import io.github.guowenlong.chatgptforandroid.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Description: 启动页
 * Author:      郭文龙
 * Date:        2023/3/31 23:04
 * Email:       guowenlong20000@sina.com
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity :BaseActivity<ActivitySplashBinding>() {

    override val layoutId: Int = R.layout.activity_splash

    override fun init(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun bind() {

    }

}