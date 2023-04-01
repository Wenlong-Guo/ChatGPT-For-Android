package io.github.guowenlong.chatgptforandroid.common.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

/**
 * Description: 基于[BaseBindingActivity]的业务基类
 * Author:      郭文龙
 * Date:        2023/3/31 22:15
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseActivity<B : ViewDataBinding> : BaseBindingActivity<B>() {

    fun showDialogFragment(fragment: DialogFragment?, tag: String) {
        fragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(fragment, tag)
            transaction.commitAllowingStateLoss()
        }
    }
}