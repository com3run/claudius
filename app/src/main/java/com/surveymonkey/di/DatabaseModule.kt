package com.surveymonkey.di

import com.surveymonkey.data.persistence.database.MobileDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { MobileDatabase.getDatabase(androidContext()).userDao() }
    single { MobileDatabase.getDatabase(androidContext()).formDao() }
    single { MobileDatabase.getDatabase(androidContext()).questionDao() }
    single { MobileDatabase.getDatabase(androidContext()).variantDao() }
    single { MobileDatabase.getDatabase(androidContext()).answerDao() }
}