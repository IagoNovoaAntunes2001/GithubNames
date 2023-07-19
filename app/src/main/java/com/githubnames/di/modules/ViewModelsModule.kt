package com.githubnames.di.modules

import com.githubnames.data.repositories.UserRepository
import com.githubnames.presentation.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { buildUserViewModelImpl(get()) }
}

private fun buildUserViewModelImpl(userRepository: UserRepository) =
    UserViewModel(userRepository = userRepository)