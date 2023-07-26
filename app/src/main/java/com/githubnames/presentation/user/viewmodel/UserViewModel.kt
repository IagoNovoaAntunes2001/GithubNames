package com.githubnames.presentation.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.githubnames.data.entities.User
import kotlinx.coroutines.flow.Flow

class UserViewModel(
    private val userPagingSource: PagingSource<Int, User>
) : ViewModel() {

    companion object {
        private const val PAGE_SIZE = 1
    }

    val listData: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        userPagingSource
    }.flow.cachedIn(viewModelScope)
}
