package com.example.littlelemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.ABORT, entity = User::class)
    suspend  fun addUser(user: User)

    @Query("SELECT * FROM users  ")
    fun getUser(): List<User>


}