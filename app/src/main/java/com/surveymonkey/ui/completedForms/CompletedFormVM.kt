package com.surveymonkey.ui.completedForms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.AnswerPOJO
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CompletedFormVM(
    private val repo: FormRepo,
    private val authRepo: AuthRepo,
    private val userId: Long = SessionManager.userId
) : BaseViewModel() {

    private val _formList = MutableLiveData<List<FormPOJO?>?>()
    val formList: LiveData<List<FormPOJO?>?> get() = _formList

    private val _uiState = MutableLiveData(UiState.LOADING)
    val uiState: LiveData<UiState> get() = _uiState

    init {
        getForms()
    }

    private fun getForms() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            val formList =
                if (SessionManager.isAdmin) {
                    mapFormList(repo.getCompletedFormsByUserId(userId))
                } else
                    mapFormList(repo.getCompletedFormsByUserId())

            _formList.value = formList

            _uiState.value = UiState.SUCCESS
        }
    }

    private suspend fun mapFormList(list: List<AnswerPOJO>?): List<FormPOJO?>? {

        return list?.map { answerPojo ->
            repo.getForm(answerPojo.formId).also { formPojo ->
                formPojo?.userId = answerPojo.userId
                formPojo?.name =
                    "${authRepo.getUserById(answerPojo.userId)?.name} - ${formPojo?.name}"
            }
        }
    }
}