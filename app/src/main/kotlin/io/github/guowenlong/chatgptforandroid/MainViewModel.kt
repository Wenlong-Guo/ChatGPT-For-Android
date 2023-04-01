package io.github.guowenlong.chatgptforandroid

import android.util.Log
import io.github.guowenlong.chatgptforandroid.common.base.BaseRepository
import io.github.guowenlong.chatgptforandroid.common.base.BaseViewModel
import io.github.guowenlong.chatgptforandroid.repository.OpenAIRepository

/**
 * Description: [MainActivity]的ViewModel
 * Author:      郭文龙
 * Date:        2023/3/31 2:38
 * Email:       guowenlong20000@sina.com
 */
class MainViewModel(private val repository: OpenAIRepository) : BaseViewModel() {
    fun getModels() = launch {
        val models = repository.getModels()
        Log.e("MainViewModel", "getModels: $models")
    }

    fun completions() = launch {
        val completion = repository.completions("埃隆马斯克的生平简介发给我")
        Log.e("MainViewModel", "completions: $completion")
    }
}