package com.nes.myapprickymorti.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Location(
    @SerialName("name") val name: String
)