package com.surveymonkey.data.persistence.dao

import androidx.room.*
import com.surveymonkey.data.persistence.entity.SavedFormEntity

@Dao
interface SavedFormDao {
    @Insert
    suspend fun insert(entity: SavedFormEntity):Long

    @Update
    suspend fun update(entity: SavedFormEntity)

    @Delete
    suspend fun delete(entity: SavedFormEntity)

    @Query("Select * from SavedForms")
    suspend fun getForms(): List<SavedFormEntity>

    @Query("Select * from SavedForms where user_id == :userId")
    suspend fun getFormsByUserId(userId: Long?): List<SavedFormEntity>

}