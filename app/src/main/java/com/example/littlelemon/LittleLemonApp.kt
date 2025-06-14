package com.example.littlelemon

import android.app.Application
import androidx.room.Room
import com.example.littlelemon.data.LittleLemonDatabase

class LittleLemonApp: Application() {

    companion object{
        lateinit var littleLemonDatabase: LittleLemonDatabase
    }

    override fun onCreate() {
        super.onCreate()

        littleLemonDatabase = Room.databaseBuilder(applicationContext, LittleLemonDatabase::class.java,"little_lemon_db").build()
    }
}