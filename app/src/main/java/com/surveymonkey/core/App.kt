package com.surveymonkey.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.surveymonkey.BuildConfig
import com.surveymonkey.di.appModule
import com.surveymonkey.di.databaseModule
import com.surveymonkey.manager.SessionManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SessionManager.init(applicationContext)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(listOf(databaseModule, appModule))
        }
    }
}