package com.surveymonkey.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.surveymonkey.data.persistence.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(userModel: UserEntity): Long

    @Update
    suspend fun update(userModel: UserEntity)

    @Delete
    suspend fun delete(userModel: UserEntity)

    @Query("Select * from Users")
    fun getUsers(): LiveData<List<UserEntity>>

    @Query("Select * from Users where id == :id")
    fun getUserById(id: Long?): UserEntity?

    @Query("Select * from Users where username == :username")
    fun getUserByUsername(username: String?): UserEntity?
}