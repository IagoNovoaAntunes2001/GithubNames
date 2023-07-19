package com.githubnames.presentation.user.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githubnames.data.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    fun getAllUsers() = viewModelScope.launch {
        val users = userRepository.getAllUsers()
        Log.i("TAG", users.toString())
    }
}