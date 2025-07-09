package com.nes.myapprickymorti.data.repository

import com.nes.myapprickymorti.data.remote.dto.CharacterApiResponse
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult

interface CharacterRepository {
    suspend fun getCharacters(): Result<CharacterApiResponse>
    suspend fun getCharacterById(id: Int): Result<CharacterResult>
    suspend fun getEpisodesByIds(ids: List<Int>): Result<List<EpisodeResult>>
}