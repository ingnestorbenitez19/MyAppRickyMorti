package com.nes.myapprickymorti.domain.use_cases.getcharacters

import com.nes.myapprickymorti.domain.repository.CharacterRepository
import javax.inject.Inject


class GetCharactersByIds @Inject constructor(private val repository: CharacterRepository){

    suspend operator fun invoke(ids: List<Int>) = repository.getCharactersByIds(ids)

}