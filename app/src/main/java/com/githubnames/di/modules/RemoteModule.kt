package com.githubnames.di.modules

import com.githubnames.BuildConfig
import com.githubnames.data.api.UserRemote
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {
    single<UserRemote> { provideRemoteInstance() }
}

private fun provideRemoteInstance() = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(UserRemote::class.java)
