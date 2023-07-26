package com.githubnames.di

import com.githubnames.di.modules.convertersModule
import com.githubnames.di.modules.remoteModule
import com.githubnames.di.modules.repositoriesModule
import com.githubnames.di.modules.viewModelsModule

val appModules = listOf(
    remoteModule,
    repositoriesModule,
    viewModelsModule,
    convertersModule
)
