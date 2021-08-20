package com.surveymonkey.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.R
import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.BaseFormModel
import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.data.model.QuestionPOJO
import com.surveymonkey.data.persistence.entity.AnswerEntity
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.SingleLiveEvent
import com.surveymonkey.utils.extensions.isValidNote
import com.surveymonkey.utils.extensions.toLongOrZero
import kotlinx.coroutines.launch

class FormVM(private val repo: FormRepo) : BaseViewModel() {
    private val _baseFormList = MutableLiveData<List<BaseFormModel?>?>()
    val baseFormList: LiveData<List<BaseFormModel?>?> get() = _baseFormList

    private val _uiState = MutableLiveData(UiState.LOADING)
    val uiState: LiveData<UiState> get() = _uiState

    private val _errorMsg = SingleLiveEvent<Int>()
    val errorMsg: LiveData<Int> get() = _errorMsg

    private var formPojo: FormPOJO? = null

    init {
        getNextForm()
    }

    private fun getNextForm() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            formPojo = repo.getForms()?.filter { formPojo ->
                repo.getCompletedFormsByUserId()?.any {
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
        if (!checkFields())
            return
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            baseFormList.value?.forEach {
                if (it is QuestionPOJO) {
                    when (it.type) {
                        QuestionType.RATE_ANSWER -> {
                            repo.insertAnswer(
                                AnswerEntity(
                                    userId = SessionManager.userId,
                                    questionId = it.id.toLongOrZero(),
                                    formId = it.formId,
                                    rate = it.rate,
                                )
                            )
                        }

                        QuestionType.NOTE_ANSWER -> {
                            repo.insertAnswer(
                                AnswerEntity(
                                    userId = SessionManager.userId,
                                    questionId = it.id.toLongOrZero(),
                                    formId = it.formId,
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
                                            formId = it.formId,
                                            variantId = variant.id?.toLong()
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                _baseFormList.value = null
                getNextForm()
            }
        }
    }

    private fun checkFields(): Boolean {
        baseFormList.value?.forEach { question ->
            if (question is QuestionPOJO) {
                when (question.type) {
                    QuestionType.NOTE_ANSWER -> {
                        if (question.required && !question.note.isValidNote()) {
                            _errorMsg.value = R.string.error_msg_form_note_validation
                            return false
                        }
                    }

                    QuestionType.MULTI_ANSWER -> {
                        val validAnswers = question.variants?.filter { it.checked }?.size ?: 0 >= 2

                        if (question.required && !validAnswers) {
                            _errorMsg.value = R.string.error_msg_form_multi_validation
                            return false
                        }
                    }
                    QuestionType.SINGLE_ANSWER -> {
                        val validAnswer = question.variants?.any { it.checked } ?: false

                        if (question.required && !validAnswer) {
                            _errorMsg.value = R.string.error_msg_form_single_validation
                            return false
                        }
                    }
                    else -> {
                    }
                }
            }
        }
        return true
    }
}