package com.example.weatherapp.data.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

object MemUtils {
    @kotlin.jvm.JvmStatic
    fun clearImages(view: View, vararg images: ImageView) {
        if (isViewFinished(view)) return
        val requestManager = Glide.with(view)
        for (imageView in images) clearImage(requestManager, imageView)
    }

    private fun isViewFinished(view: View): Boolean {
        if (view.context is AppCompatActivity) {
            val appCompatActivity = view.context as AppCompatActivity
            return appCompatActivity.isDestroyed || appCompatActivity.isFinishing
        }
        return false
    }
    private fun clearImage(requestManager: RequestManager, imageView: ImageView) {
        requestManager.clear(imageView)
        imageView.setImageDrawable(null)
    }
}