package io.github.guowenlong.chatgptforandroid.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Description: 下载工具类
 * Author:      郭文龙
 * Date:        2023/4/10 22:49
 * Email:       guowenlong20000@sina.com
 */
object DownloadUtils {
    fun downloadPic(
        context: Context,
        picUrl: String,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        val fileName = picUrl.substring(picUrl.lastIndexOf("/") + 1) + "chatgpt.jpg"
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        Glide.with(context)
            .asBitmap()
            .load(picUrl)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    try {
                        val fileOutputStream = FileOutputStream(file)
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                        fileOutputStream.flush()
                        fileOutputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    onSuccess.invoke(file.absolutePath)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    onError.invoke()
                }
            })
    }
}