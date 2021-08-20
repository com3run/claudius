package com.surveymonkey.data.persistence.dummy

import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.persistence.dao.FormDao
import com.surveymonkey.data.persistence.dao.QuestionDao
import com.surveymonkey.data.persistence.dao.VariantDao
import com.surveymonkey.data.persistence.entity.FormEntity
import com.surveymonkey.data.persistence.entity.QuestionEntity
import com.surveymonkey.data.persistence.entity.VariantEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class Form2  {
    fun form() = FormEntity(name = "Form2 Customer Satisfaction Survey Template")

    fun question1(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How likely is it that you would recommend this company to a friend or colleague?",
        type = QuestionType.RATE_ANSWER
    )

    fun question2(formId: Long) = QuestionEntity(
        formId = formId,
        name = "Overall, how satisfied or dissatisfied are you with our company?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question2Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very satisfied")

    fun question2Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Somewhat satisfied")

    fun question2Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Neither satisfied nor dissatisfied")

    fun question2Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Somewhat dissatisfied")

    fun question2Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very dissatisfied")

    fun question3(formId: Long) = QuestionEntity(
        formId = formId,
        name = "Which of the following words would you use to describe our products? Select all that apply.",
        type = QuestionType.MULTI_ANSWER,
        required = true
    )

    fun question3Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Reliable")

    fun question3Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "High quality")

    fun question3Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Useful")

    fun question3Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Unique")

    fun question3Variant5(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Good value for money"
    )

    fun question3Variant6(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Overpriced"
    )

    fun question3Variant7(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Impractical"
    )

    fun question3Variant8(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Ineffective"
    )

    fun question3Variant9(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Poor quality"
    )

    fun question3Variant10(questionId: Long) = VariantEntity(
        questionId = questionId, name = "Unreliable"
    )

    fun question4(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How well do our products meet your needs?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question4Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Extremely well")

    fun question4Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very well")

    fun question4Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Somewhat well")

    fun question4Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not so well")

    fun question4Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not at all well")


    fun question5(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How would you rate the quality of the product?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question5Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very high quality")

    fun question5Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "High quality")

    fun question5Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Neither high nor low quality")

    fun question5Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Low quality")

    fun question5Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very low quality")


    fun question6(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How would you rate the value for money of the product?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question6Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Excellent")

    fun question6Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Above average")

    fun question6Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Average")

    fun question6Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Below average")

    fun question6Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Poor")


    fun question7(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How responsive have we been to your questions or concerns about our products?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question7Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Extremely responsive")

    fun question7Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very responsive")

    fun question7Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Somewhat responsive")

    fun question7Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not so responsive")

    fun question7Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not at all responsive")

    fun question7Variant6(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not applicable")


    fun question8(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How long have you been a customer of our company?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question8Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "This is my first purchase")

    fun question8Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Less than six months")

    fun question8Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Six months to a year")

    fun question8Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "1 - 2 years")

    fun question8Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "3 or more years")

    fun question8Variant6(questionId: Long) =
        VariantEntity(questionId = questionId, name = "I haven't made a purchase yet")


    fun question9(formId: Long) = QuestionEntity(
        formId = formId,
        name = "How likely are you to purchase any of our products again?",
        type = QuestionType.SINGLE_ANSWER,
        required = true
    )

    fun question9Variant1(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Extremely likely")

    fun question9Variant2(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Very likely")

    fun question9Variant3(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Somewhat likely")

    fun question9Variant4(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not so likely")

    fun question9Variant5(questionId: Long) =
        VariantEntity(questionId = questionId, name = "Not at all likely")


    fun question10(formId: Long) = QuestionEntity(
        formId = formId,
        name = "Do you have any other comments, questions, or concerns?",
        type = QuestionType.NOTE_ANSWER
    )

    fun create(formDao: FormDao, questionDao: QuestionDao, variantDao: VariantDao) {
        GlobalScope.launch {
            val formId = formDao.insert(form())
            questionDao.insert(question1(formId = formId))

            val question2Id = questionDao.insert(question2(formId = formId))
            variantDao.insert(variantModel = question2Variant1(questionId = question2Id))
            variantDao.insert(variantModel = question2Variant2(questionId = question2Id))
            variantDao.insert(variantModel = question2Variant3(questionId = question2Id))

            val question3Id = questionDao.insert(question3(formId = formId))
            variantDao.insert(variantModel = question3Variant1(questionId = question3Id))
            variantDao.insert(variantModel = question3Variant2(questionId = question3Id))
            variantDao.insert(variantModel = question3Variant3(questionId = question3Id))
            variantDao.insert(variantModel = question3Variant4(questionId = question3Id))
            variantDao.insert(variantModel = question3Variant5(questionId = question3Id))
            variantDao.insert(variantModel = question3Variant6(questionId = question3Id))

            val question4Id = questionDao.insert(question4(formId = formId))
            variantDao.insert(variantModel = question4Variant1(questionId = question4Id))
            variantDao.insert(variantModel = question4Variant2(questionId = question4Id))
            variantDao.insert(variantModel = question4Variant3(questionId = question4Id))
            variantDao.insert(variantModel = question4Variant4(questionId = question4Id))
            variantDao.insert(variantModel = question4Variant5(questionId = question4Id))

            val question5Id = questionDao.insert(question5(formId = formId))
            variantDao.insert(variantModel = question5Variant1(questionId = question5Id))
            variantDao.insert(variantModel = question5Variant2(questionId = question5Id))
            variantDao.insert(variantModel = question5Variant3(questionId = question5Id))
            variantDao.insert(variantModel = question5Variant4(questionId = question5Id))
            variantDao.insert(variantModel = question5Variant5(questionId = question5Id))

            val question6Id = questionDao.insert(question6(formId = formId))
            variantDao.insert(variantModel = question6Variant1(questionId = question6Id))
            variantDao.insert(variantModel = question6Variant2(questionId = question6Id))
            variantDao.insert(variantModel = question6Variant3(questionId = question6Id))
            variantDao.insert(variantModel = question6Variant4(questionId = question6Id))
            variantDao.insert(variantModel = question6Variant5(questionId = question6Id))

            val question7Id = questionDao.insert(question7(formId = formId))
            variantDao.insert(variantModel = question7Variant1(questionId = question7Id))
            variantDao.insert(variantModel = question7Variant2(questionId = question7Id))
            variantDao.insert(variantModel = question7Variant3(questionId = question7Id))
            variantDao.insert(variantModel = question7Variant4(questionId = question7Id))
            variantDao.insert(variantModel = question7Variant5(questionId = question7Id))
            variantDao.insert(variantModel = question7Variant6(questionId = question7Id))

            val question8Id = questionDao.insert(question8(formId = formId))
            variantDao.insert(variantModel = question8Variant1(questionId = question8Id))
            variantDao.insert(variantModel = question8Variant2(questionId = question8Id))
            variantDao.insert(variantModel = question8Variant3(questionId = question8Id))
            variantDao.insert(variantModel = question8Variant4(questionId = question8Id))
            variantDao.insert(variantModel = question8Variant5(questionId = question8Id))

            val question9Id = questionDao.insert(question9(formId = formId))
            variantDao.insert(variantModel = question9Variant1(questionId = question9Id))
            variantDao.insert(variantModel = question9Variant2(questionId = question9Id))
            variantDao.insert(variantModel = question9Variant3(questionId = question9Id))
            variantDao.insert(variantModel = question9Variant4(questionId = question9Id))
            variantDao.insert(variantModel = question9Variant5(questionId = question9Id))

            questionDao.insert(question10(formId = formId))

            Timber.e("Form1 create")
        }
    }
}