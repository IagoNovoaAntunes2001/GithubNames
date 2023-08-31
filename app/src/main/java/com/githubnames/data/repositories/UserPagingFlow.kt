package com.githubnames.data.repositories

import androidx.paging.PagingData
import com.githubnames.data.entities.User
import kotlinx.coroutines.flow.Flow

interface UserPagingFlow {
    fun getPagingData(): Flow<PagingData<User>>
}