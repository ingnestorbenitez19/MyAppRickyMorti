package com.nes.myapprickymorti.domain.use_cases.getcharacters

import com.nes.myapprickymorti.data.remote.dto.CharacterResponseDTO
import com.nes.myapprickymorti.domain.repository.CharacterRepository
import javax.inject.Inject


class GetCharacters @Inject constructor(private val repository: CharacterRepository){

    //operator fun invoke() = repository.getCharacters()

    //operator fun invoke(): Flow<PagingData<CharacterDTO>> = repository.getCharacters()

    suspend operator fun invoke(page: Int): CharacterResponseDTO = repository.getCharacters(page)

}