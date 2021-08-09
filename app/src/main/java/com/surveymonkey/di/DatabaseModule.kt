package com.surveymonkey.di

import com.surveymonkey.data.persistence.database.MobileDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { MobileDatabase.getDatabase(androidApplication()).userDao() }
    single { MobileDatabase.getDatabase(androidApplication()).formDao() }
    single { MobileDatabase.getDatabase(androidApplication()).questionDao() }
    single { MobileDatabase.getDatabase(androidApplication()).variantDao() }
    single { MobileDatabase.getDatabase(androidApplication()).answerDao() }
    single { MobileDatabase.getDatabase(androidApplication()).savedFormDao() }
}