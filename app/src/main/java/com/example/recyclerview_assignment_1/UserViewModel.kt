package com.example.recyclerview_assignment_1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    val getAllUser: LiveData<List<User>> = userDao.getAllUser()

}