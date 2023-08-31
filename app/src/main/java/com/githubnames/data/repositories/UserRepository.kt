package com.githubnames.data.repositories

import com.githubnames.data.dto.UserDTO

interface UserRepository {
    suspend fun getAllUsers(): List<UserDTO>
}
