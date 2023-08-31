package com.githubnames.domain.user

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.githubnames.data.entities.User
import com.githubnames.data.repositories.UserPagingFlow
import com.githubnames.data.repositories.UserRepository
import com.githubnames.domain.user.converter.UserDataConverter
import kotlinx.coroutines.flow.Flow

class UserPagingFlowImpl(
    private val userRepository: UserRepository,
    private val userDataConverter: UserDataConverter
) : UserPagingFlow {

    companion object {
        private const val PAGE_SIZE = 1
    }
    override fun getPagingData(): Flow<PagingData<User>> {
        return Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            UserPagingSource(userRepository, userDataConverter)
        }.flow
    }
}