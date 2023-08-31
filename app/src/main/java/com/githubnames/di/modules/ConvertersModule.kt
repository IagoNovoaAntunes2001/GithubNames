package com.githubnames.di.modules

import com.githubnames.domain.user.converter.UserDataConverter
import org.koin.dsl.module

val convertersModule = module {
    single { provideUserDataConverter() }
}

private fun provideUserDataConverter() = UserDataConverter()
