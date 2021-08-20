package com.surveymonkey.data.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.surveymonkey.data.persistence.dao.*
import com.surveymonkey.data.persistence.dummy.Form1
import com.surveymonkey.data.persistence.dummy.Form2
import com.surveymonkey.data.persistence.entity.*
import timber.log.Timber

@Database(
    entities =
    [UserEntity::class, FormEntity::class, AnswerEntity::class,
        QuestionEntity::class, VariantEntity::class, SavedFormEntity::class],
    version = 1
)
@TypeConverters(QuestionTypeConverter::class)
abstract class MobileDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun formDao(): FormDao
    abstract fun questionDao(): QuestionDao
    abstract fun variantDao(): VariantDao
    abstract fun answerDao(): AnswerDao

    companion object {
        @Volatile
        private var INSTANCE: MobileDatabase? = null

        fun getDatabase(context: Context): MobileDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, MobileDatabase::class.java, "mobile_db")
                        .addCallback(callback)
                        .fallbackToDestructiveMigration()
                        .build()

                INSTANCE = instance
                instance
            }
        }

        private val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                Timber.e("RoomDatabase onCreate")

                INSTANCE?.let {
                    Form1().create(
                        formDao = it.formDao(),
                        questionDao = it.questionDao(),
                        variantDao = it.variantDao()
                    )

                    Form2().create(
                        formDao = it.formDao(),
                        questionDao = it.questionDao(),
                        variantDao = it.variantDao()
                    )
                }
            }
        }
    }
}