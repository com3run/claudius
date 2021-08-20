package com.surveymonkey.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.surveymonkey.R
import com.surveymonkey.databinding.ActivityMainBinding
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.utils.hideSoftKeyboard

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupWithNavController()
    }

    private fun setupWithNavController() {
        setSupportActionBar(binding.toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHost
        val navController = navHost.navController

        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(
                setOf(
                    R.id.loginFragment,
                    R.id.formFragment,
                    if (SessionManager.isAdmin) R.id.usersFragment else 0
                )
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()

        onBackPressed()
        return true
    }
}