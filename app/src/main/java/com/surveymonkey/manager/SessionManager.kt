package com.surveymonkey.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.surveymonkey.BuildConfig


object SessionManager {
    private const val KEY_USER_ID = "user_id"
    private const val KEY_USER_NAME = "username"
    private const val KEY_LOGGED_IN = "logged_in"

    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref =
            EncryptedSharedPreferences.create(
                context, "secret_shared_prefs",
                createMasterKey(context),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }

    var loggedIn
        get() = sharedPref.getBoolean(KEY_LOGGED_IN, false)
        set(value) {
            sharedPref.edit { putBoolean(KEY_LOGGED_IN, value) }
        }

    var userId
        get() = sharedPref.getLong(KEY_USER_ID, 0)
        set(value) {
            sharedPref.edit { putLong(KEY_USER_ID, value) }
        }

    var username
        get() = sharedPref.getString(KEY_USER_NAME, "")
        set(value) {
            sharedPref.edit { putString(KEY_USER_NAME, value) }
        }

    val isAdmin
        get() = username == BuildConfig.USERNAME_ADMIN


    fun clearData() {
        sharedPref.edit().clear().apply()
    }

    private fun createMasterKey(context: Context) =
        MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
}