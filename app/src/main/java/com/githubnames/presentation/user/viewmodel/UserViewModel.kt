package com.githubnames.presentation.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.githubnames.data.entities.User
import com.githubnames.data.repositories.UserPagingFlow
import kotlinx.coroutines.flow.Flow

class UserViewModel(
    private val userPagingFlow: UserPagingFlow
) : ViewModel() {

    val listData: Flow<PagingData<User>> = userPagingFlow.getPagingData().cachedIn(viewModelScope)
}
