package com.surveymonkey.di

import com.surveymonkey.data.model.FormPOJO
import com.surveymonkey.data.repo.AuthRepo
import com.surveymonkey.data.repo.FormRepo
import com.surveymonkey.data.repo.UserRepo
import com.surveymonkey.ui.auth.login.LoginVM
import com.surveymonkey.ui.auth.register.RegisterVM
import com.surveymonkey.ui.main.FormVM
import com.surveymonkey.ui.completedForms.CompletedFormVM
import com.surveymonkey.ui.completedForms.detail.CompletedFormDetailVM
import com.surveymonkey.ui.completedForms.users.UserVM
import com.surveymonkey.ui.splash.SplashVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashVM() }

    factory { AuthRepo(get()) }
    viewModel { LoginVM(get()) }
    viewModel { RegisterVM(get()) }

    factory { UserRepo(get()) }
    viewModel { UserVM(get()) }

    factory { FormRepo(get(), get(), get(), get()) }
    viewModel { FormVM(get()) }
    viewModel { (userId: Long) -> CompletedFormVM(get(), get(), userId) }
    viewModel { (formPojo: FormPOJO) -> CompletedFormDetailVM(get(), formPojo) }
}