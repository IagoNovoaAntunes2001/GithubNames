package com.githubnames.domain.user

import com.githubnames.data.api.UserRemote
import com.githubnames.data.repositories.UserRepository

class UserRepositoryImpl(
    private val userRemote: UserRemote
) : UserRepository {
    override suspend fun getAllUsers() = userRemote.getAllUsers()
}
