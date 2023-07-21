package com.githubnames.presentation.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.githubnames.domain.user.UserPagingSource
import kotlinx.coroutines.launch

class UserViewModel(
    private val userPagingSource: UserPagingSource
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        userPagingSource
    }.flow.cachedIn(viewModelScope)
}