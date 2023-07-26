package com.githubnames.di.modules

import com.githubnames.domain.user.UserPagingSource
import com.githubnames.presentation.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { provideUserViewModel(get()) }
}

private fun provideUserViewModel(pagingSource: UserPagingSource) =
    UserViewModel(userPagingSource = pagingSource)
