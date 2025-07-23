package com.nes.myapprickymorti.domain.use_cases.getcharacters

//class GetCharactersUseCase(
//    private val repository: CharacterRepository
//) {
//    suspend operator fun invoke(): Result<List<CharacterResult>> {
//        return repository.getCharacters().map { response ->
//            response.results
//        }
//    }
//}


data class GetCharactersUseCase(
    val getCharacters: GetCharacters,
    val getCharacterById: GetCharacterById
)



//class GetCharactersUseCase @Inject constructor(
//    private val repository: CharacterRepository
//) {
//    operator fun invoke(): Flow<PagingData<CharacterDTO>> = repository.getCharacters()
//}