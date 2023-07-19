package com.githubnames.data.api

import retrofit2.http.GET

interface UserApi {

    @GET("/users")
    suspend fun getAllUsers() {

    }
}