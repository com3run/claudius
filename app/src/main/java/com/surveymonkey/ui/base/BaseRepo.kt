package com.surveymonkey.ui.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepo(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun <T> call(
        func: suspend () -> T
    ): T {
        return withContext(dispatcher) {
                func.invoke()
        }
    }
}