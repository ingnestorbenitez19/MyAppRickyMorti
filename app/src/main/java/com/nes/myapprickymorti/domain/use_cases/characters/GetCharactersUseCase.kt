package com.nes.myapprickymorti.domain.use_cases.characters

import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import com.nes.myapprickymorti.data.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<CharacterResult>> {
        return repository.getCharacters().map { response ->
            response.results
        }
    }
}

