package com.surveymonkey.utils.extensions

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.supportActionBar(): ActionBar? {
    return appCompatActivity().supportActionBar
}

fun Fragment.appCompatActivity(): AppCompatActivity {
    return activity as AppCompatActivity
}

fun Fragment.toast(message: Any?) {
    context?.toast(message)
}
