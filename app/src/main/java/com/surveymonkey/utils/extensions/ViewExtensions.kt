package com.surveymonkey.utils.extensions


import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.button.MaterialButton
import com.surveymonkey.R
import com.surveymonkey.data.enums.UiState

fun MaterialButton.handleProgress(uiState: UiState) {
    isEnabled = uiState != UiState.LOADING

    when (uiState) {
        UiState.LOADING -> {
            if (!text.isNullOrEmpty())
                tag = text.toString()

            showProgress { progressColor = context.getColorAttrs(R.attr.colorOnBackground) }
        }
        else -> {
            hideProgress(newText = tag?.toString())
        }
    }
}

fun RecyclerView.smoothScrollToPositionTop(position: Int) {
    val smoothScroller = object : LinearSmoothScroller(context) {
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}