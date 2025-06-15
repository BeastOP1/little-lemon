package com.example.littlelemon

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.littlelemon.data.LittleLemonDatabase
import com.example.littlelemon.data.PreferenceKeys
import kotlinx.coroutines.flow.MutableStateFlow

class LittleLemonApp: Application() {

    companion object{
        lateinit var littleLemonDatabase: LittleLemonDatabase
        lateinit var userPrefer : SharedPreferences
         var startDestination : MutableStateFlow<String> = MutableStateFlow("")



    }

    override fun onCreate() {
        super.onCreate()

        littleLemonDatabase = Room.databaseBuilder(applicationContext, LittleLemonDatabase::class.java,"little_lemon_db").build()

        userPrefer =  applicationContext.getSharedPreferences("user",MODE_PRIVATE)
        startDestination.value = if(userPrefer.getString(PreferenceKeys.EMAIL.name,"")!!.isNotEmpty()){
            "home"
        }else {
            "register"
        }

    }






}