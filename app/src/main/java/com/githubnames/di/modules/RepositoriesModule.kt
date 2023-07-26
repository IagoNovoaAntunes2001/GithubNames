package com.githubnames.di.modules

import androidx.paging.PagingSource
import com.githubnames.data.api.UserRemote
import com.githubnames.data.entities.User
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.UserPagingSource
import com.githubnames.domain.user.UserRepositoryImpl
import com.githubnames.domain.user.converter.UserDataConverter
import org.koin.dsl.module

val repositoriesModule = module {
    single<UserRepository> { provideUserRepository(get()) }
    single<PagingSource<Int, User>> { provideUserPagingSource(get(), get()) }
}

private fun provideUserRepository(userRemote: UserRemote) =
    UserRepositoryImpl(userRemote = userRemote)

private fun provideUserPagingSource(
    userRepository: UserRepository,
    userDataConverter: UserDataConverter
) =
    UserPagingSource(userRepository = userRepository, userDataConverter = userDataConverter)
