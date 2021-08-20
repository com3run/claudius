package com.surveymonkey.data.repo

import com.surveymonkey.data.model.asPOJO
import com.surveymonkey.data.persistence.dao.*
import com.surveymonkey.data.persistence.entity.AnswerEntity
import com.surveymonkey.data.persistence.entity.SavedFormEntity
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseRepo
import timber.log.Timber


class FormRepo(
    private val formDao: FormDao,
    private val questionDao: QuestionDao,
    private val variantDao: VariantDao,
    private val answerDao: AnswerDao
) : BaseRepo() {

    suspend fun getForms() = call {
        formDao.getForms().asPOJO()
    }

    suspend fun getForm(formId: Long?) = call {
        formDao.getForm(formId).asPOJO()
    }

    suspend fun getQuestions(formId: Long?) = call {
        questionDao.getQuestionsByFormId(formId).asPOJO()
    }

    suspend fun getVariants(questionId: Long?) = call {
        variantDao.getVariantsByQuestionId(questionId).asPOJO()
    }

    suspend fun getAnswerByIds(userId: Long?, formId: Long?, questionId: Long?) = call {
        answerDao.getAnswerByIds(
            userId = userId,
            formId = formId,
            questionId = questionId
        ).asPOJO()
    }


    suspend fun insertAnswer(answerEntity: AnswerEntity) = call {
        answerDao.insert(answerEntity)
    }

    suspend fun getCompletedForms() = call {
        answerDao.getCompletedForms().asPOJO()
    }

    suspend fun getCompletedFormsByUserId(userId: Long = SessionManager.userId) = call {
        answerDao.getCompletedFormsByUserId(userId).asPOJO()
    }
}