package com.surveymonkey.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surveymonkey.data.persistence.entity.FormEntity

@Dao
interface FormDao {
    @Insert
    suspend fun insert(formModel: FormEntity): Long

    @Update
    suspend fun update(formModel: FormEntity)

    @Delete
    suspend fun delete(formModel: FormEntity)

    @Query("Select * from Forms")
    suspend fun getForms(): List<FormEntity>

    @Query("Select * from Forms")
    fun getFormsLiveData(): LiveData<List<FormEntity>>

    @Query("Select * from Forms where id == :formId limit 1")
    suspend fun getForm(formId: Long?): FormEntity

    @Query("Select * from Forms order by RANDOM() limit 1")
    fun getRandomForm(): FormEntity
}