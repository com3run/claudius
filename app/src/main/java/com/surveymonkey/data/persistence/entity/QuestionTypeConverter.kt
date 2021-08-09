package com.surveymonkey.data.persistence.entity

import androidx.room.TypeConverter
import com.surveymonkey.data.enums.QuestionType

class QuestionTypeConverter {
    @TypeConverter
    fun fromQuestionType(value: QuestionType?): String? {
        return value?.let { it.name }
    }

    @TypeConverter
    fun toQuestionType(type: String?): QuestionType? {
        return type?.let { QuestionType.valueOf(type)}
    }
}