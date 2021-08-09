package com.surveymonkey.utils.extensions

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes


fun Int.dpToPx(): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
        .toInt()
}

fun Int.spToPx(): Float {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this * fontScale + 0.5f)
}

fun Int?.toLongOrZero() = this?.toLong() ?: 0L

fun Long?.toIntOrZero() = this?.toInt() ?: 0

fun Context?.toast(message: Any?) {
    this?.let {
        Toast.makeText(it, message.toString(), Toast.LENGTH_SHORT).show()
    }
}

fun Context?.getColorAttrs(@AttrRes resId: Int): Int {
    val typedValue = TypedValue()
    this?.theme?.resolveAttribute(resId, typedValue, true)

    return typedValue.data
}