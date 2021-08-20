package com.surveymonkey.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Answers")
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "user_id")
    val userId: Long?=null,
    @ColumnInfo(name = "question_id")
    val questionId: Long? = null,
    @ColumnInfo(name = "form_Id")
    val formId: Long?,
    @ColumnInfo(name = "variant_id")
    val variantId: Long? = null,
    @ColumnInfo(name = "note")
    val note: String? = null,
    @ColumnInfo(name = "rate")
    val rate: Int? = null
)