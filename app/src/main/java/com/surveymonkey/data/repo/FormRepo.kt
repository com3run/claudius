package com.surveymonkey.data.repo

import com.surveymonkey.data.model.asPOJO
import com.surveymonkey.data.persistence.dao.*
import com.surveymonkey.data.persistence.entity.AnswerEntity
import com.surveymonkey.data.persistence.entity.SavedFormEntity
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseRepo


class FormRepo(
    private val formDao: FormDao,
    private val questionDao: QuestionDao,
    private val variantDao: VariantDao,
    private val answerDao: AnswerDao,
    private val savedFormDao: SavedFormDao,
) : BaseRepo() {

    suspend fun getRandomForm(userId: Long = SessionManager.userId) = call {
        formDao.getRandomForm().asPOJO()
    }

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

    suspend fun getAnswerByIds(userId: Long?, savedFormId: Long?, questionId: Long?) = call {
        answerDao.getAnswerByIds(
            userId = userId,
            savedFormId = savedFormId,
            questionId = questionId
        ).asPOJO()
    }

    suspend fun insertForm(savedFormEntity: SavedFormEntity) = call {
        savedFormDao.insert(savedFormEntity)
    }

    suspend fun insertAnswer(answerEntity: AnswerEntity) = call {
        answerDao.insert(answerEntity)
    }

    suspend fun getSavedForms() = call {
        savedFormDao.getForms().asPOJO()
    }

    suspend fun getSavedFormsByUserId(userId: Long = SessionManager.userId) = call {
        savedFormDao.getFormsByUserId(userId).asPOJO()
    }
}