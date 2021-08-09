package com.surveymonkey.data.model

data class VariantPOJO(
    val id: Int? = null,
    val questionId: Long?,
    val name: String?,
    var checked: Boolean = false
)