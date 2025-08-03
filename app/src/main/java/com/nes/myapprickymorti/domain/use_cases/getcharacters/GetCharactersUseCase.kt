package com.nes.myapprickymorti.domain.use_cases.getcharacters



data class GetCharactersUseCase(
    val getCharacters: GetCharacters,
    val getCharacterById: GetCharacterById,
    val getEpisodesByIds: GetEpisodesByIds,
    val getCharactersByIds: GetCharactersByIds
)
