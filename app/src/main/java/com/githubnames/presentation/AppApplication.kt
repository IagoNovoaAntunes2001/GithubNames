package com.githubnames.presentation

import android.app.Application
import com.githubnames.di.appModules
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}