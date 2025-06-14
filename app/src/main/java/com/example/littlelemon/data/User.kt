package com.example.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    val firstName : String,
    val lastName : String,
    @PrimaryKey
    val email: String
)
