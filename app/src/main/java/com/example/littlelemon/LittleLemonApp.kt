package com.example.littlelemon

import android.app.Application
import android.content.SharedPreferences
import android.provider.Settings
import androidx.room.Room
import com.example.littlelemon.data.LittleLemonDatabase
import com.example.littlelemon.data.PreferenceKeys
import com.example.littlelemon.data.remote.Response
import com.example.littlelemon.ui.theme.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class LittleLemonApp: Application() {

    companion object{
        lateinit var littleLemonDatabase: LittleLemonDatabase
        lateinit var userPrefer : SharedPreferences
         var startDestination : MutableStateFlow<String> = MutableStateFlow("")

        lateinit var httpClient: HttpClient
    }

    override fun onCreate() {
        super.onCreate()

        littleLemonDatabase = Room.databaseBuilder(applicationContext, LittleLemonDatabase::class.java,"little_lemon_db").build()

        userPrefer =  applicationContext.getSharedPreferences(Constant.USER_PREFERENCE,MODE_PRIVATE)
        httpClient = HttpClient(Android){
            install(ContentNegotiation){
                json(contentType = ContentType("text", "plain"))
            }
            install(HttpTimeout){
                requestTimeoutMillis = 10000
            }

        }

        startDestination.value = if(userPrefer.getString(PreferenceKeys.EMAIL.name,"")!!.isNotEmpty()){
            Routes.HOME.name
        }else {
            Routes.REGISTER.name
        }

    }








}