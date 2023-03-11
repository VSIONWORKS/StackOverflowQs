package com.exam.stackoverflowqs.core

import android.app.Application
import com.exam.stackoverflowqs.core.modules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Dependency injection core setup
 * */
class StackOverflowQsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@StackOverflowQsApp)
            modules(appModules)
        }
    }
}