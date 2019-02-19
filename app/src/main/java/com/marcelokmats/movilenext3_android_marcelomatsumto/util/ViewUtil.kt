package com.marcelokmats.movilenext3_android_marcelomatsumto.util

import android.view.View

object ViewUtil {

    enum class Type {
        CONTENT,
        PROGRESSBAR,
        ERROR
    }

    /**
     * Changes the content, progress bar and error message views visibility accordingly to the "view type"
     */
    fun changeViewMode(content: View, progressBar: View, errorMessage: View, type: ViewUtil.Type) {
        when (type) {
            ViewUtil.Type.CONTENT -> {
                content.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                errorMessage.visibility = View.GONE
            }
            ViewUtil.Type.PROGRESSBAR -> {
                content.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                errorMessage.visibility = View.GONE
            }
            ViewUtil.Type.ERROR -> {
                content.visibility = View.GONE
                progressBar.visibility = View.GONE
                errorMessage.visibility = View.VISIBLE
            }
        }
    }

}
