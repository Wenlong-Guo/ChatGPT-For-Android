package io.github.guowenlong.chatgptforandroid.common.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Description: repository的基类
 * Author:      郭文龙
 * Date:        2023/3/31 1:27
 * Email:       guowenlong20000@sina.com
 */
abstract class BaseRepository {

    suspend fun <T : Any> request(call: suspend () -> T): T {
        return withContext(Dispatchers.IO) { call.invoke() }.apply {
            Log.i("BaseRepository", this.toString())
        }
    }
}