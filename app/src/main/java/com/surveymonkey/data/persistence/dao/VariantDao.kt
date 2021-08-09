package com.surveymonkey.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surveymonkey.data.persistence.entity.QuestionEntity
import com.surveymonkey.data.persistence.entity.VariantEntity

@Dao
interface VariantDao {
    @Insert
    suspend fun insert(variantModel: VariantEntity)

    @Update
    suspend fun update(variantModel: VariantEntity)

    @Delete
    suspend fun delete(variantModel: VariantEntity)

    @Query("Select * from Variants where question_id == :questionId")
    suspend fun getVariantsByQuestionId(questionId: Long?): List<VariantEntity>

}