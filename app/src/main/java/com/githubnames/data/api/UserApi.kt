package com.githubnames.data.api

import com.githubnames.data.dto.UserDTO
import retrofit2.http.GET

interface UserApi {

    @GET("/users")
    suspend fun getAllUsers(): List<UserDTO>
}