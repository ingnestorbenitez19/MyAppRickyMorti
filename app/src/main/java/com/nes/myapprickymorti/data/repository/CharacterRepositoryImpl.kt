package com.nes.myapprickymorti.data.repository


import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.data.remote.dto.CharacterResponseDTO
import com.nes.myapprickymorti.data.remote.dto.EpisodeDTO
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult
import com.nes.myapprickymorti.data.remote.dto.InfoDTO
import com.nes.myapprickymorti.domain.repository.CharacterRepository
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val apiService: RickAndMortyApiService
) : CharacterRepository {


    override suspend fun getCharacters(page: Int): CharacterResponseDTO {

        val response = apiService.getCharacters(page)
        return CharacterResponseDTO(
            info = InfoDTO(
                count = response.info.count,
                pages = response.info.pages,
                next = response.info.next,
                prev = response.info.prev
            ),
            results = response.results.map { characterResult ->
                CharacterDTO(
                    id = characterResult.id,
                    name = characterResult.name,
                    status = characterResult.status,
                    species = characterResult.species,
                    type = characterResult.type,
                    gender = characterResult.gender,
                    image = characterResult.image,
                    episode = characterResult.episode
                )
            }
        )
    }


    override suspend fun getCharacterById(id: Int): CharacterDTO {
        val result = apiService.getCharacterById(id)
        return CharacterDTO(
            id = result.id,
            name = result.name,
            status = result.status,
            species = result.species,
            type = result.type,
            gender = result.gender,
            image = result.image,
            episode = result.episode
        )
    }

    override suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeDTO> {
        if (ids.isEmpty()) return emptyList()
        return if (ids.size == 1) {
            val episode = apiService.getEpisodeById(ids.first())
            listOf(
                EpisodeDTO(
                    id = episode.id,
                    name = episode.name,
                    episode = episode.episode,
                    air_date = episode.air_date
                )
            )
        } else {
            apiService.getEpisodesByIds(ids.joinToString(",")).map { episode ->
                EpisodeDTO(
                    id = episode.id,
                    name = episode.name,
                    episode = episode.episode,
                    air_date = episode.air_date
                )
            }
        }
    }

    override suspend fun getCharactersByIds(ids: List<Int>): List<CharacterDTO> {

        if (ids.isEmpty()) return emptyList()
        return if (ids.size == 1) {
            val character = apiService.getCharacterById(ids.first())
            listOf(
                CharacterDTO(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    image = character.image,
                    episode = character.episode
                )
            )
        } else {

            apiService.getCharactersByIds(ids.joinToString(",")).map { character ->
                CharacterDTO(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    image = character.image,
                    episode = character.episode
                )
            }
        }



    }




}