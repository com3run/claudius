package com.surveymonkey.data.model

data class AnswerPOJO(
    val id: Int?,
    val userId: Long?,
    val questionId: Long?,
    val variantId: Long?,
    val note: String?,
    val rate: Int?
)