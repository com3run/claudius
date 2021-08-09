package com.surveymonkey.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surveymonkey.data.persistence.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Insert
    suspend fun insert(questionModel: QuestionEntity): Long

    @Update
    suspend fun update(questionModel: QuestionEntity)

    @Delete
    suspend fun delete(questionModel: QuestionEntity)

    @Query("Select * from Questions where form_id == :formId")
    suspend fun getQuestionsByFormId(formId: Long?): List<QuestionEntity>

}