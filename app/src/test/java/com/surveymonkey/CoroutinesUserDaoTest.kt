package com.surveymonkey

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.surveymonkey.data.persistence.dao.UserDao
import com.surveymonkey.data.persistence.database.MobileDatabase
import com.surveymonkey.di.appModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject



open class CoroutinesUserDaoTest:KoinTest  {

    val userDb: MobileDatabase by inject()
    val userDAO: UserDao by inject()

    @Before
    fun setup() {

        loadKoinModules(appModule)

    }

    @After
    fun after() {
        userDb.close()
    }

    @Test
    fun testSave() {

        Log.e("Data","sad")
    }
}