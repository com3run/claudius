package com.surveymonkey.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.surveymonkey.data.enums.QuestionType

@Entity(tableName = "Questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "form_id")
    val formId: Long?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "type")
    val type: QuestionType? = QuestionType.NOTE_ANSWER,
    @ColumnInfo(name = "required")
    val required: Boolean = false
)