package com.surveymonkey.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.surveymonkey.data.enums.QuestionType

@Entity(tableName = "Variants")
data class VariantEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "question_id")
    val questionId: Long?,
    @ColumnInfo(name = "name")
    val name: String?
)