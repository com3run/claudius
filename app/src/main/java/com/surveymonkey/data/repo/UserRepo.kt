package com.surveymonkey.data.repo

import com.surveymonkey.data.model.asPOJO
import com.surveymonkey.data.model.asPojoList
import com.surveymonkey.data.persistence.dao.UserDao
import com.surveymonkey.ui.base.BaseRepo


class UserRepo(private val dao: UserDao) : BaseRepo() {
    suspend fun getUsers() = call {
        dao.getUsers().asPojoList()
    }

    suspend fun getUserById(id: Long?) = call {
        dao.getUserById(id).asPOJO()
    }
}