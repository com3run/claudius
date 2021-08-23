package com.surveymonkey.data.persistence.dummy

import com.surveymonkey.data.persistence.entity.UserEntity

class DataCreator {

    fun createUsers(count: Int): List<UserEntity> {

        val userList: ArrayList<UserEntity> = ArrayList()
        for (i in 0..count) {
            userList.add(UserEntity(id = i, name = "Alex $i", username = "alex_$i"))
        }

        return userList
    }

    fun createUser(): UserEntity {
        return UserEntity(id = 1, name = "Kamran", username = "com3run")
    }
    fun createUser2(): UserEntity {
        return UserEntity(id = 2, name = "Kamran2", username = "com3run2")
    }
}