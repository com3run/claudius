package com.surveymonkey.utils.bindingAdapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.surveymonkey.utils.OnSingleClickListener

@BindingAdapter("bind:onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

@BindingAdapter("bind:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:textRes")
fun TextView.setText(resId: Int) {
    if (resId > 0)
        this.setText(resId)
}