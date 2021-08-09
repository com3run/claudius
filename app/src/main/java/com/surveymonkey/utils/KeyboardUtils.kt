package com.surveymonkey.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun TextInputLayout.showSoftKeyboard() {
    GlobalScope.launch(Dispatchers.Main) {
        delay(50)

        editText?.let {
            it.requestFocus()
            it.setSelection(it.text.length)

            val imm =
                context?.let {
                    ContextCompat.getSystemService(
                        context,
                        InputMethodManager::class.java
                    )
                }
            imm?.showSoftInput(it, 0)
        }
    }
}

fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}

fun Activity.hideSoftKeyboard() {
    window.decorView.hideSoftKeyboard()
}

fun View?.hideSoftKeyboard() {
    this?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(it.context, InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}