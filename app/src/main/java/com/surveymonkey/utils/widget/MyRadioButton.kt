package com.surveymonkey.utils.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.recyclerview.widget.RecyclerView
import com.surveymonkey.R
import com.surveymonkey.data.model.VariantPOJO
import com.surveymonkey.utils.extensions.dpToPx
import com.surveymonkey.utils.extensions.getColorAttrs
import com.surveymonkey.utils.extensions.spToPx


@SuppressLint("ResourceType")
class MyRadioButton(context: Context) : AppCompatRadioButton(context) {
    var onChange: () -> Unit = {}

    init {
        rootView.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        setTextSize(TypedValue.COMPLEX_UNIT_PX, 16.spToPx())
        setPadding(16.dpToPx(), 8.dpToPx(), 16.dpToPx(), 8.dpToPx())
        setTextColor(context.getColorAttrs(R.attr.colorOnBackground))
    }

    fun setPojo(pojo: VariantPOJO) {
        this.id = pojo.id ?: View.generateViewId()

        isChecked = pojo.checked
        text = pojo.name

        setOnCheckedChangeListener { _, checked ->
            pojo.checked = checked

            countDownTimer.cancel()
            countDownTimer.start()
        }
    }

    private val countDownTimer = object : CountDownTimer(400, 100) {
        override fun onTick(p0: Long) {}
        override fun onFinish() {
            onChange()
        }
    }
}