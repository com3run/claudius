package com.surveymonkey.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.R
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.ErrorDialogModel
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.SingleLiveEvent
import com.surveymonkey.utils.extensions.toLongOrZero
import kotlinx.coroutines.launch

class LoginVM(private val repo: AuthRepo) : BaseViewModel() {
    val username = MutableLiveData<String>()

    private val _loginUiState = MutableLiveData<UiState>()
    val loginUiState: LiveData<UiState> get() = _loginUiState

    private val _navigateToNext = SingleLiveEvent<Boolean>()
    val navigateToNext: LiveData<Boolean> get() = _navigateToNext

    fun login() {
        viewModelScope.launch {
            _loginUiState.value = UiState.LOADING

            val userModel = repo.getUserByUsername(username = username.value)

            if (userModel != null) {
                val username = username.value?.lowercase()

                SessionManager.loggedIn = true
                SessionManager.username = username
                SessionManager.userId = userModel.id.toLongOrZero()

                _navigateToNext.value = true
                _loginUiState.value = UiState.SUCCESS
            } else {
                showError(ErrorDialogModel(messageRes = R.string.user_not_fount))

                _loginUiState.value = UiState.ERROR
            }
        }
    }
}