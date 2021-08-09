package com.surveymonkey.data.model

import com.surveymonkey.data.persistence.entity.*


fun UserEntity?.asPOJO(): UserPOJO? {
    return this?.let {
        return UserPOJO(
            id = it.id,
            username = it.username,
            name = it.name,
        )
    }
}

fun FormEntity?.asPOJO(): FormPOJO? {
    return this?.let {
        return FormPOJO(
            id = it.id,
            name = it.name
        )
    }
}

fun SavedFormEntity?.asPOJO(): SavedFormPOJO? {
    return this?.let {
        return SavedFormPOJO(
            id = it.id,
            formId = it.formId,
            userId = it.userId,
            name = ""
        )
    }
}

fun QuestionEntity?.asPOJO(): QuestionPOJO? {
    return this?.let {
        return QuestionPOJO(
            id = it.id,
            formId = it.formId,
            question = it.question,
            type = it.type,
        )
    }
}

fun AnswerEntity?.asPOJO(): AnswerPOJO? {
    return this?.let {
        return AnswerPOJO(
            id = it.id,
            userId = it.userId,
            variantId = it.variantId,
            questionId = it.questionId,
            note = it.note,
            rate = it.rate,
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

@JvmName("asPOJOSavedFormEntity")
fun List<SavedFormEntity>?.asPOJO(): List<SavedFormPOJO>? {
    return this?.map { it.asPOJO()!! }
}

@JvmName("asPOJOFormEntity")
fun List<FormEntity>?.asPOJO(): List<FormPOJO>? {
    return this?.map { it.asPOJO()!! }
}