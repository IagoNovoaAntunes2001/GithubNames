package com.githubnames.di.modules

import com.githubnames.data.api.UserRemote
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single { provideRemoteInstance() }
}

private fun provideRemoteInstance(): UserRemote = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(UserRemote::class.java)