package com.githubnames.domain.user

import com.githubnames.data.dto.UserDTO
import com.githubnames.data.entities.User

object Mock {
    fun getUsersDto() = listOf(
        UserDTO(id = 1, login = "User 1", avatar_url = ""),
        UserDTO(id = 2, login = "User 2", avatar_url = ""),
        UserDTO(id = 3, login = "User 3", avatar_url = "")
    )

    fun getUsers() = listOf(
        User(id = 1, login = "User 1", avatar_url = ""),
        User(id = 2, login = "User 2", avatar_url = ""),
        User(id = 3, login = "User 3", avatar_url = "")
    )
}