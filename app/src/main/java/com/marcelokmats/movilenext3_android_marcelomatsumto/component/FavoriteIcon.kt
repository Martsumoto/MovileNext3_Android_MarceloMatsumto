package com.marcelokmats.movilenext3_android_marcelomatsumto.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.marcelokmats.movilenext3_android_marcelomatsumto.R

class FavoriteIcon : AppCompatImageView, View.OnClickListener {

    private var mContext: Context? = null

    var isActive = false
        private set


    private var mOnFavoriteStateChanged : (() -> Unit)? = null

    constructor(context: Context) : super(context) {
        this.init(context, null)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        this.init(context, attrs)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mContext = context
        this.setOnClickListener(this)
        this.setImageDrawable(
            ContextCompat.getDrawable(context, R.drawable.ic_heart_empty))
    }

    override fun onClick(view: View) {
        this.isActive = !this.isActive
        this.setActive(this.isActive)
        this.mOnFavoriteStateChanged?.invoke()
    }

    fun setActive(active: Boolean) {
        this.isActive = active

        val drawable: Drawable? = if (active) {
            ContextCompat.getDrawable(context, R.drawable.ic_heart_filled)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_heart_empty)
        }

        this.setImageDrawable(drawable)
    }

    fun setOnFavoriteStateChangedListener(onChanged: () -> Unit) {
        this.mOnFavoriteStateChanged = onChanged
    }
}
