package io.github.guowenlong.chatgptforandroid.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.guowenlong.chatgptforandroid.common.components.SpProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.koin.java.KoinJavaComponent

/**
 * Description: ViewModel的基类
 * Author:      郭文龙
 * Date:        2023/3/31 2:38
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseViewModel : ViewModel() {

    val sp: SpProperty by KoinJavaComponent.inject(SpProperty::class.java)

    fun launch(
        timeoutMillis: Long = 5 * 60 * 1000,
        block: suspend CoroutineScope.() -> Unit
    ) =
        viewModelScope.launch {
            try {
                withTimeout(timeoutMillis) { block() }
            } catch (e: Exception) {
                Log.e("BaseViewModel", "launch: $block", e)
            }
        }
}