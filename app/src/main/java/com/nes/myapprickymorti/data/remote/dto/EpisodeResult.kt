package com.nes.myapprickymorti.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResult(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("episode") val episode: String

)


