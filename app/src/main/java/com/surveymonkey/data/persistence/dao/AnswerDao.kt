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


    @Query("Select * from Answers where user_id == :userId and question_id == :questionId and saved_form_Id == :savedFormId")
    fun getAnswerByIds(userId: Long?, savedFormId: Long?, questionId: Long?): List<AnswerEntity>?

    @Query("Select * from Answers")
    fun getAnswers(): List<AnswerEntity>
}