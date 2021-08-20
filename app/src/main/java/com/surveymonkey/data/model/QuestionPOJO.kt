package com.surveymonkey.data.model

import com.surveymonkey.data.enums.QuestionType

data class QuestionPOJO(
    override var id: Int? = null,
    val formId: Long?,
    val name: String?,
    val type: QuestionType? = QuestionType.NOTE_ANSWER,
    var variants: List<VariantPOJO>? = listOf(),
    var note: String? = "",
    var rate: Int? = 5,
    var required: Boolean = false
) : BaseFormModel()