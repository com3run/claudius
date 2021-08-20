package com.surveymonkey.data.model

import com.surveymonkey.data.persistence.entity.*


fun UserEntity?.asPOJO(): UserPOJO? {
    return this?.let {
        return UserPOJO(
            id = it.id?:0,
            username = it.username,
            name = it.name
        )
    }
}


fun FormEntity?.asPOJO(): FormPOJO? {
    return this?.let {
        return FormPOJO(
            id = it.id,
            name = it.name)
    }
}

fun QuestionEntity?.asPOJO(): QuestionPOJO? {
    return this?.let {
        return QuestionPOJO(
            id = it.id,
            formId = it.formId,
            name = it.name,
            type = it.type,
            required = it.required
        )
    }
}

fun AnswerEntity?.asPOJO(): AnswerPOJO? {
    return this?.let {
        return AnswerPOJO(
            id = it.id,
            userId = it.userId,
            formId = it.formId,
            variantId = it.variantId,
            questionId = it.questionId,
            note = it.note,
            rate = it.rate
        )
    }
}

fun VariantEntity?.asPOJO(): VariantPOJO? {
    return this?.let {
        return VariantPOJO(
            id = it.id,
            questionId = it.questionId,
            name = it.name,
        )
    }
}

 fun List<UserEntity>?.asPojoList(): List<UserPOJO>? {
    return this?.map { it.asPOJO()!! }
}

@JvmName("asPOJOQuestionEntity")
fun List<QuestionEntity>?.asPOJO(): List<QuestionPOJO>? {
    return this?.map { it.asPOJO()!! }
}

@JvmName("asPOJOVariantEntity")
fun List<VariantEntity>?.asPOJO(): List<VariantPOJO>? {
    return this?.map { it.asPOJO()!! }
}

@JvmName("asPOJOAnswerEntity")
fun List<AnswerEntity>?.asPOJO(): List<AnswerPOJO>? {
    return this?.map { it.asPOJO()!! }
}

@JvmName("asPOJOFormEntity")
fun List<FormEntity>?.asPOJO(): List<FormPOJO>? {
    return this?.map { it.asPOJO()!! }
}