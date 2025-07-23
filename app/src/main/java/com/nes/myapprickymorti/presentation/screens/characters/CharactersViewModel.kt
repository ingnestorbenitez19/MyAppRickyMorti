package com.nes.myapprickymorti.presentation.screens.characters


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersResponse: GetCharactersUseCase
) : ViewModel() {



    //val characters: Flow<PagingData<CharacterDTO>> = charactersUseCases.getCharacters().cachedIn(viewModelScope)

//    val characters: Flow<PagingData<CharacterDTO>> = charactersResponse.getCharacters().cachedIn(viewModelScope)



//    var charactersResponse by mutableStateOf<Response<List<CharacterDTO>>?>(null)
//
//    init {
//        getCharacters()
//    }
//
//    fun getCharacters() = viewModelScope.launch {
//
//        charactersResponse = Response.Loading
//        charactersUseCases.getCharacters().collect() { response ->
//            charactersResponse = response
//        }
//    }



    private val _characters = MutableStateFlow<Response<List<CharacterDTO>>>(Response.Loading)
    val characters: StateFlow<Response<List<CharacterDTO>>> = _characters


    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    private var currentPage = 1
    private var isLastPage = false


    private val loadedCharacters = mutableListOf<CharacterDTO>()

    private val _filteredCharacters = MutableStateFlow<List<CharacterDTO>>(emptyList())
    val filteredCharacters: StateFlow<List<CharacterDTO>> = _filteredCharacters



    init {
        loadCharacters()
    }

    fun loadCharacters() {
        if (isLastPage) return

        viewModelScope.launch {
            _characters.value = Response.Loading
            try {
                val response = charactersResponse.getCharacters(currentPage)
                loadedCharacters.addAll(response.results)
                _characters.value = Response.Success(loadedCharacters.toList())
                isLastPage = response.info.next == null
                currentPage++
                filterCharacters(_searchText.value)
            } catch (e: Exception) {
                _characters.value = Response.Failure(e)
            }
        }
    }


    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
        filterCharacters(newText)
    }


    private fun filterCharacters(query: String) {
        val filtered = if (query.isBlank()) {
            loadedCharacters
        } else {
            loadedCharacters.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        _filteredCharacters.value = filtered
    }

    fun getCharacterById(id: Int): CharacterDTO? {
        return loadedCharacters.find { it.id == id }
    }

    suspend fun getCharacterDetailFromApi(id: Int): CharacterDTO {
        return charactersResponse.getCharacterById(id)
    }


}