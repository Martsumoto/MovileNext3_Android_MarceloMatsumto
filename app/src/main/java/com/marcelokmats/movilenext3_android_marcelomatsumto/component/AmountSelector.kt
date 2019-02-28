package com.marcelokmats.movilenext3_android_marcelomatsumto.component

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.marcelokmats.movilenext3_android_marcelomatsumto.R

class AmountLayout : LinearLayout {

    companion object {
        const val MAX_VALUE = 100;
    }

    lateinit var btPlus : Button
        private set
    lateinit var btMinus : Button
        private set
    lateinit var tvAmount : TextView
        private set

    constructor(context: Context) : super(context) {
        this.init(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        this.init(context)
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.number_picker, this)
        btPlus = findViewById(R.id.plus)
        btMinus = findViewById(R.id.minus)

        btPlus.setOnClickListener { changeAmount(1) }
        btMinus.setOnClickListener { changeAmount(-1) }
    }

    private fun changeAmount(factor: Int) {
        var amount = getCurrentAmount()
        amount += factor
        amount = when {
            amount < 0 -> 0
            amount > MAX_VALUE -> MAX_VALUE
            else -> amount
        }
        setAmount(amount)
    }

    private fun getCurrentAmount() = tvAmount.text.toString().toIntOrNull() ?: 0

    private fun setAmount(amount: Int) {
        tvAmount.text = amount.toString()
    }
}