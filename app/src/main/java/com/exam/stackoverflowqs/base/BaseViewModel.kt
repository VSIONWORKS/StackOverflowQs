package com.exam.stackoverflowqs.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exam.stackoverflowqs.utils.Constants.THROWABLE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun safeLaunch(dispatcher: CoroutineDispatcher, method: suspend () -> Unit) {
        viewModelScope.launch(errorHandler + dispatcher) {
            method.invoke()
        }
    }

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onExceptionReceived(throwable)

    }

    open fun onExceptionReceived(e: Throwable) {
        Log.e(THROWABLE, "$e")
    }
}