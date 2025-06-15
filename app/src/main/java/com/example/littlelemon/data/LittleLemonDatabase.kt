package com.example.littlelemon.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.littlelemon.data.remote.Menu

@Database(entities = [Menu::class], version = 1, exportSchema = false)
abstract class LittleLemonDatabase : RoomDatabase(){

    abstract fun menuDao(): MenuDao


}