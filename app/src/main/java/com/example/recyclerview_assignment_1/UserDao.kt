package com.example.recyclerview_assignment_1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUser(): LiveData<List<User>>
}