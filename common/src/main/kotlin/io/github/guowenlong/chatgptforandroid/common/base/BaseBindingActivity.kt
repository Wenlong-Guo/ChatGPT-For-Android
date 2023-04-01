package io.github.guowenlong.chatgptforandroid.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.github.guowenlong.chatgptforandroid.common.ext.logD

/**
 * Description: [AppCompatActivity]带[ViewDataBinding]的基类
 * Author:      郭文龙
 * Date:        2023/3/31 22:16
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseBindingActivity<B : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: B

    abstract val layoutId: Int

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun bind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD("current Activity ->${javaClass.name}")
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        init(savedInstanceState)
        bind()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}