package com.githubnames.presentation

import android.app.Application
import com.githubnames.di.modules.remoteModule
import com.githubnames.di.modules.repositoriesModule
import com.githubnames.di.modules.viewModelsModule
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                remoteModule,
                repositoriesModule,
                viewModelsModule
            )
        }
    }
}