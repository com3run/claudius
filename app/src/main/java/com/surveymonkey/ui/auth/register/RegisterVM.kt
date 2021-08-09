package com.surveymonkey.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.R
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.ErrorDialogModel
import com.surveymonkey.data.persistence.entity.UserEntity
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class RegisterVM(private val repo: AuthRepo) : BaseViewModel() {
    val name = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    private val _registerUiState = MutableLiveData<UiState>()
    val registerUiState: LiveData<UiState> get() = _registerUiState

    private val _navigateToForm = SingleLiveEvent<Boolean>()
    val navigateToForm: LiveData<Boolean> get() = _navigateToForm

    fun register() {
        viewModelScope.launch {
            _registerUiState.value = UiState.LOADING

            val userModel = repo.getUserByUsername(username = username.value)

            if (userModel != null) {
                showError(ErrorDialogModel(messageRes = R.string.username_already_exists))

                _registerUiState.value = UiState.ERROR
            } else {
                val name = name.value
                val username = username.value

                val userId = repo.insert(UserEntity(name = name, username = username))

                SessionManager.loggedIn = true
                SessionManager.username = username
                SessionManager.userId = userId

                _navigateToForm.value = true
                _registerUiState.value = UiState.SUCCESS
            }
        }
    }
}