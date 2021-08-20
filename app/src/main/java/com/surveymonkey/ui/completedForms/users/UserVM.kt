package com.surveymonkey.ui.completedForms.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.data.model.UserPOJO
import com.surveymonkey.data.repo.UserRepo
import com.surveymonkey.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class UserVM(private val repo: UserRepo) :
    BaseViewModel() {

    private val _userList = MutableLiveData<List<UserPOJO>>()
    val userList: LiveData<List<UserPOJO>> get() = _userList

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            Timber.e(repo.getUsers().toString())

            _userList.value = repo.getUsers()
        }
    }
}