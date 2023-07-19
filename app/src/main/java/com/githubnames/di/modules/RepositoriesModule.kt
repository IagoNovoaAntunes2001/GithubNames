package com.githubnames.di.modules

import com.githubnames.data.api.UserRemote
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    single<UserRepository> { buildUserRepositoryImpl(get()) }
}

private fun buildUserRepositoryImpl(userRemote: UserRemote) =
    UserRepositoryImpl(userRemote = userRemote)