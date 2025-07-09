package com.nes.myapprickymorti.data.repository

import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.remote.dto.CharacterApiResponse
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult


class CharacterRepositoryImpl(
    private val apiService: RickAndMortyApiService
) : CharacterRepository {
    override suspend fun getCharacters(): Result<CharacterApiResponse> {
        return try {
            val response = apiService.getCharacters()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCharacterById(id: Int): Result<CharacterResult> {
        return try {
            val response = apiService.getCharacterById(id)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEpisodesByIds(ids: List<Int>): Result<List<EpisodeResult>> {
        return try {
            val idsString = ids.joinToString(",")
            val response = apiService.getEpisodesByIds(idsString)
            val episodes = when (response) {
                is List<*> -> response.filterIsInstance<EpisodeResult>()
                is EpisodeResult -> listOf(response)
                else -> emptyList()
            }
            Result.success(episodes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}