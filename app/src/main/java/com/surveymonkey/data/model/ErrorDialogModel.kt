package com.surveymonkey.data.model

import androidx.annotation.StringRes


class ErrorDialogModel(
    @StringRes val titleRes: Int = 0,
    @StringRes var messageRes: Int = 0,
    var cancelable: Boolean = true,
    var showDialog: Boolean = true,
    var positiveButton: ButtonModel = ButtonModel(),
    var negativeButton: ButtonModel = ButtonModel(text = 0),
)