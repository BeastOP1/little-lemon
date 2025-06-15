package com.example.littlelemon.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "menus")
data class Menu(

    @SerialName("id")
    @PrimaryKey
    val id : Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")

    val description : String,

    @SerialName("price")
    val price: String,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
)
