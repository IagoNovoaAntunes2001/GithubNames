package com.githubnames.domain.user.converter

import com.githubnames.data.dto.UserDTO
import com.githubnames.data.entities.User

class UserDataConverter {
    fun convert(dto: List<UserDTO>): List<User> {
        return dto.map {
            User(
                id = it.id,
                login = it.login,
                avatar_url = it.avatar_url
            )
        }
    }
}