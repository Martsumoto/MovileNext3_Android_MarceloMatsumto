package com.marcelokmats.movilenext3_android_marcelomatsumto.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageUtil {
    companion object {
        fun loadImage(context: Context, imageUrl: String, imageView: ImageView) {
            Glide.with(context)
                .load(imageUrl)
                .into(imageView)
        }
    }
}