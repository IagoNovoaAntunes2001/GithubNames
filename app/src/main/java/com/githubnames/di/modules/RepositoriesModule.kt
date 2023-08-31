package com.githubnames.di.modules

import com.githubnames.data.api.UserRemote
import com.githubnames.data.repositories.UserPagingFlow
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.UserPagingFlowImpl
import com.githubnames.domain.user.UserRepositoryImpl
import com.githubnames.domain.user.converter.UserDataConverter
import org.koin.dsl.module

val repositoriesModule = module {
    single<UserRepository> { provideUserRepository(get()) }
    factory<UserPagingFlow> { provideUserPagingFLow(get(), get()) }
}

private fun provideUserRepository(userRemote: UserRemote) =
    UserRepositoryImpl(userRemote = userRemote)

private fun provideUserPagingFLow(
    userRepository: UserRepository,
    userDataConverter: UserDataConverter
) =
    UserPagingFlowImpl(userRepository = userRepository, userDataConverter = userDataConverter)
