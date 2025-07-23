package com.nes.myapprickymorti.domain.use_cases.getcharacters

import com.nes.myapprickymorti.domain.repository.CharacterRepository
import javax.inject.Inject


class GetCharacterById @Inject constructor(private val repository: CharacterRepository){

    suspend operator fun invoke(id: Int) = repository.getCharacterById(id)

}