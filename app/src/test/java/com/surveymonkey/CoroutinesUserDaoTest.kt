package com.surveymonkey

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surveymonkey.data.persistence.dao.UserDao
import com.surveymonkey.data.persistence.database.MobileDatabase
import com.surveymonkey.data.persistence.dummy.DataCreator
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner.Silent::class)
open class CoroutinesUserDaoTest : KoinTest {

    @get:Rule
    val instant=InstantTaskExecutorRule()
    @Mock
    lateinit var mockContext: Context
    lateinit var userDao: UserDao

    @Before
    fun setup() {
        userDao = MobileDatabase.getDatabase(mockContext).userDao()
    }

    @Test
    fun testSave() {
        runBlocking {
            instant.runCatching {

                val testUser =  DataCreator().createUser()
                val testUser2 =  DataCreator().createUser2()
                userDao.insert(testUser)

                val user = userDao.getUserById(testUser.id?.toLong())

                println(testUser)
                println(testUser2)

                Assert.assertEquals(testUser2,user)

                println("test")
            }
        }
    }
}