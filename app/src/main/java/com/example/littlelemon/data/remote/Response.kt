package com.example.littlelemon.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
 data class Response(
    @SerialName("menu")
    val menu: List<Menu>
)
