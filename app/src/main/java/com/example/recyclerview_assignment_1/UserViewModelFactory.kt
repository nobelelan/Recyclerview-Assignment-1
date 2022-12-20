package com.example.recyclerview_assignment_1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val userDao: UserDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userDao) as T
    }
}