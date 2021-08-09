package com.surveymonkey.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashVM : BaseViewModel() {

    private val _isUserLoggedIn = SingleLiveEvent<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn

    init {
        viewModelScope.launch {
            delay(1500)

            _isUserLoggedIn.value = SessionManager.loggedIn
        }
    }
}