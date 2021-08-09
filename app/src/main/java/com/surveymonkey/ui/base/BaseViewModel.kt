package com.surveymonkey.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surveymonkey.data.model.ErrorDialogModel

abstract class BaseViewModel : ViewModel() {
    private val _showErrorDialog = MutableLiveData<ErrorDialogModel?>()
    val showErrorDialog: LiveData<ErrorDialogModel?> get() = _showErrorDialog

    fun showError(model: ErrorDialogModel?) {
        _showErrorDialog.value = model
    }
}