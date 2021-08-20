package com.surveymonkey.data.persistence.dao

import androidx.room.*
import com.surveymonkey.data.persistence.entity.AnswerEntity

@Dao
interface AnswerDao {
    @Insert
    suspend fun insert(answerModel: AnswerEntity): Long

    @Update
    suspend fun update(answerModel: AnswerEntity)

    @Delete
    suspend fun delete(answerModel: AnswerEntity)


    @Query("Select * from Answers where user_id == :userId and question_id == :questionId and form_Id == :formId")
    fun getAnswerByIds(userId: Long?, formId: Long?, questionId: Long?): List<AnswerEntity>?

    @Query("Select * from Answers group by form_id")
    fun getCompletedForms(): List<AnswerEntity>?

    @Query("Select * from Answers where user_id == :userId group by form_id")
    fun getCompletedFormsByUserId(userId: Long?): List<AnswerEntity>?

    @Query("Select * from Answers")
    fun getAnswers(): List<AnswerEntity>
}