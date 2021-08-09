package com.surveymonkey.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.SavedFormPOJO
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SavedVM(private val repo: FormRepo, private val authRepo: AuthRepo) : BaseViewModel() {
    private val _formList = MutableLiveData<List<SavedFormPOJO>?>()
    val formList: LiveData<List<SavedFormPOJO>?> get() = _formList

    private val _uiState = MutableLiveData(UiState.LOADING)
    val uiState: LiveData<UiState> get() = _uiState

    init {
        getRandomForm()
    }

    private fun getRandomForm() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            val formList =
                if (SessionManager.isAdmin) {
                    repo.getSavedForms()?.map {
                        it.also {
                            it.name =
                                "${authRepo.getUserById(it.userId)?.name} ${repo.getForm(it.formId)?.name}"
                        }
                    }
                } else
                    repo.getSavedFormsByUserId()?.map {
                        it.also { it.name = repo.getForm(it.formId)?.name }
                    }

            _formList.value = formList

            _uiState.value = UiState.SUCCESS
        }
    }
}