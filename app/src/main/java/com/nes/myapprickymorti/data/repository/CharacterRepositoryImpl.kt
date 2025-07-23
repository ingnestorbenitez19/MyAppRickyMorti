package com.nes.myapprickymorti.data.repository


import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.data.remote.dto.CharacterResponseDTO
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
                    image = characterResult.image
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
            image = result.image
        )
    }



//    override fun getCharactersHardcode(): Flow<Response<List<CharacterDTO>>> = callbackFlow {
//
//        val dummyCharacters = listOf(
//            CharacterDTO(
//                id = 1,
//                name = "Rick Sanchez"
//            ),
//            CharacterDTO(
//                id = 2,
//                name = "Morty Smith"
//            )
//        )
//
//        trySend(Response.Success(dummyCharacters))
//
//        awaitClose {}
//    }

}