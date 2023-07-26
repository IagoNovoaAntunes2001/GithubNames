package com.githubnames.di.modules

import androidx.paging.PagingSource
import com.githubnames.data.entities.User
import com.githubnames.presentation.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { provideUserViewModel(get()) }
}

private fun provideUserViewModel(pagingSource: PagingSource<Int, User>) =
    UserViewModel(userPagingSource = pagingSource)
