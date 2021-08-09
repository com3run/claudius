package com.surveymonkey.data.model

import com.surveymonkey.data.enums.QuestionType
import com.surveymonkey.data.persistence.entity.QuestionEntity

abstract class BaseFormModel{
    open var id: Int?=null
}