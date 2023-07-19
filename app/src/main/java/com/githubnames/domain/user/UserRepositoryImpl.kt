package com.githubnames.domain.user

import com.githubnames.data.api.UserApi
import com.githubnames.data.repositories.UserRepository

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun getAllUsers() = userApi.getAllUsers()
}
