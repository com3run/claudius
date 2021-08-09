package com.surveymonkey.di

import com.surveymonkey.data.model.SavedFormPOJO
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.ui.auth.login.LoginVM
import com.surveymonkey.ui.auth.register.RegisterVM
import com.surveymonkey.ui.main.FormVM
import com.surveymonkey.ui.saved.SavedVM
import com.surveymonkey.ui.saved.detail.SavedDetailVM
import com.surveymonkey.ui.splash.SplashVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashVM() }

    factory { AuthRepo(get()) }
    viewModel { LoginVM(get()) }
    viewModel { RegisterVM(get()) }

    factory { FormRepo(get(), get(), get(), get(), get()) }
    viewModel { FormVM(get()) }
    viewModel { SavedVM(get(),get()) }
    viewModel { (savedFormPojo: SavedFormPOJO) -> SavedDetailVM(get(), savedFormPojo) }
}