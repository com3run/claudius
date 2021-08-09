package com.surveymonkey.data.repo

import com.surveymonkey.data.model.asPOJO
import com.surveymonkey.data.persistence.dao.UserDao
import com.surveymonkey.data.persistence.entity.UserEntity
import com.surveymonkey.ui.base.BaseRepo


class AuthRepo(private val dao: UserDao) : BaseRepo() {
    suspend fun insert(userModel: UserEntity) = call {
        dao.insert(userModel)
    }

    suspend fun update(userModel: UserEntity) = call {
        dao.update(userModel)
    }

    suspend fun delete(userModel: UserEntity) = call {
        dao.delete(userModel)
    }

    suspend fun getUserByUsername(username: String?) = call {
        dao.getUserByUsername(username).asPOJO()
    }

    suspend fun getUserById(id: Long?) = call {
        dao.getUserById(id).asPOJO()
    }
}