package com.surveymonkey.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.BaseFormModel
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.data.persistence.entity.AnswerEntity
import com.surveymonkey.data.persistence.entity.SavedFormEntity
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.extensions.toLongOrZero
import kotlinx.coroutines.launch

class FormVM(private val repo: FormRepo) : BaseViewModel() {
    private val _baseFormList = MutableLiveData<List<BaseFormModel?>>()
    val baseFormList: LiveData<List<BaseFormModel?>> get() = _baseFormList

    private val _uiState = MutableLiveData(UiState.LOADING)
    val uiState: LiveData<UiState> get() = _uiState

    private var formPojo: FormPOJO? = null

    init {
        getRandomForm()
    }

    private fun getRandomForm() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            formPojo = repo.getForms()?.filter { formPojo ->
                repo.getSavedFormsByUserId()?.any {
                    it.formId == formPojo.id.toLongOrZero()
                } == false
            }?.getOrNull(0)


            if (formPojo != null) {
                val baseFormList = arrayListOf<BaseFormModel?>()
                baseFormList.add(formPojo)

                repo.getQuestions(formPojo?.id.toLongOrZero())?.forEach {
                    when (it.type) {
                        QuestionType.MULTI_ANSWER,
                        QuestionType.SINGLE_ANSWER -> {
                            it.variants = repo.getVariants(it.id.toLongOrZero())
                        }

                        else -> {
                        }
                    }

                    baseFormList.add(it)
                }

                _baseFormList.value = baseFormList

                _uiState.value = UiState.SUCCESS
            } else {
                _uiState.value = UiState.EMPTY
            }
        }
    }

    fun saveForm() {
        baseFormList.value?.let {

            viewModelScope.launch {
                _uiState.value = UiState.LOADING

                val savedFormId = repo.insertForm(
                    SavedFormEntity(
                        formId = formPojo?.id.toLongOrZero(),
                        userId = SessionManager.userId
                    )
                )

                it.forEach {
                    if (it is QuestionPOJO) {
                        when (it.type) {
                            QuestionType.RATE_ANSWER -> {
                                repo.insertAnswer(
                                    AnswerEntity(
                                        userId = SessionManager.userId,
                                        questionId = it.id.toLongOrZero(),
                                        savedFormId = savedFormId,
                                        rate = it.rate,
                                    )
                                )
                            }

                            QuestionType.NOTE_ANSWER -> {
                                repo.insertAnswer(
                                    AnswerEntity(
                                        userId = SessionManager.userId,
                                        questionId = it.id.toLongOrZero(),
                                        savedFormId = savedFormId,
                                        note = it.note,
                                    )
                                )
                            }

                            QuestionType.MULTI_ANSWER, QuestionType.SINGLE_ANSWER -> {
                                it.variants?.forEach { variant ->
                                    if (variant.checked) {
                                        repo.insertAnswer(
                                            AnswerEntity(
                                                userId = SessionManager.userId,
                                                questionId = it.id.toLongOrZero(),
                                                savedFormId = savedFormId,
                                                variantId = variant.id?.toLong()
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                _baseFormList.value=null
                getRandomForm()
            }
        }
    }
}