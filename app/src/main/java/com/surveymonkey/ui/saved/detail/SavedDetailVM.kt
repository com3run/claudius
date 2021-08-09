package com.surveymonkey.ui.saved.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.enums.UiState
import com.surveymonkey.data.model.BaseFormModel
import com.surveymonkey.data.model.SavedFormPOJO
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.ui.base.BaseViewModel
import com.surveymonkey.utils.extensions.toLongOrZero
import kotlinx.coroutines.launch

class SavedDetailVM(private val repo: FormRepo, private val savedFormPOJO: SavedFormPOJO?) :
    BaseViewModel() {
    private val _baseFormList = MutableLiveData<List<BaseFormModel?>>()
    val baseFormList: LiveData<List<BaseFormModel?>> get() = _baseFormList

    private val _uiState = MutableLiveData(UiState.LOADING)
    val uiState: LiveData<UiState> get() = _uiState

    init {
        getRandomForm()
    }

    private fun getRandomForm() {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING

            val formPOJO = repo.getForm(savedFormPOJO?.formId)

            val baseFormList = arrayListOf<BaseFormModel?>()
            baseFormList.add(formPOJO)

            repo.getQuestions(formPOJO?.id.toLongOrZero())?.forEach {
                val answers =
                    repo.getAnswerByIds(
                        userId = savedFormPOJO?.userId,
                        savedFormId = savedFormPOJO?.id?.toLong(),
                        questionId = it.id.toLongOrZero()
                    )

                when (it.type) {
                    QuestionType.RATE_ANSWER -> {
                        it.rate = answers?.getOrNull(0)?.rate
                    }
                    QuestionType.NOTE_ANSWER -> {
                        it.note = answers?.getOrNull(0)?.note
                    }

                    else -> {
                        val list = repo.getVariants(it.id.toLongOrZero())
                            ?.map { variant ->
                                variant.also {
                                    variant.checked = answers?.any { answer ->
                                        answer.variantId == variant.id.toLongOrZero()
                                    } == true
                                }
                            }

                        it.variants = list
                    }
                }

                baseFormList.add(it)
            }

            _baseFormList.value = baseFormList

            _uiState.value = UiState.SUCCESS
        }
    }
}