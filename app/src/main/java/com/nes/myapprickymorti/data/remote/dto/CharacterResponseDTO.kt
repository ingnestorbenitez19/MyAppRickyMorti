package com.nes.myapprickymorti.data.remote.dto



data class CharacterResponseDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>
)