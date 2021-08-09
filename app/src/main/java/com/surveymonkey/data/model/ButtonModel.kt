package com.surveymonkey.data.model

import android.view.View
import androidx.annotation.StringRes
import com.surveymonkey.R

data class ButtonModel(
    @StringRes var text: Int = R.string.ok,
    var onClickListener: View.OnClickListener? = null
)