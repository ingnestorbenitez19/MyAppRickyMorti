package com.nes.myapprickymorti.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterApiResponse(
    @SerialName("info") val info: Info,
    @SerialName("results") val results: List<CharacterResult>
)


