package com.nes.myapprickymorti.data.remote.dto

import com.google.gson.Gson

data class CharacterDTO(

    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val image: String = "",
    val episode: List<String> = emptyList()

){

    fun toJson(): String = Gson().toJson(CharacterDTO(
        id,
        name,
        status,
        species,
        type,
        gender,
        image,
        episode
    ))

    companion object{
        fun fromJson(data: String): CharacterDTO = Gson().fromJson(data, CharacterDTO::class.java)
    }

}
