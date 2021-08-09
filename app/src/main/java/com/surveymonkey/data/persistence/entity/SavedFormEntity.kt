package com.surveymonkey.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedForms")
data class SavedFormEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "form_id")
    val formId: Long?,
    @ColumnInfo(name = "user_id")
    val userId: Long?
)