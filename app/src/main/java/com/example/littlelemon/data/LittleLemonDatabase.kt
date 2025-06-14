package com.example.littlelemon.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class LittleLemonDatabase : RoomDatabase(){

    abstract fun uerDao(): UserDao



}