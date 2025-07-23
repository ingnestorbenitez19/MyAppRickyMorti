package com.nes.myapprickymorti.presentation.screens.list

//data class CharacterUiState(
//    val characters: List<CharacterResult> = emptyList(),
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null
//)
//
//
//
//@HiltViewModel
//class CharacterViewModel @Inject constructor(
//    private val getCharactersUseCase: GetCharactersUseCase
//) : ViewModel() {
//
//    private val _uiState = MutableStateFlow(CharacterUiState())
//    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()
//
//    init {
//        loadCharacters()
//    }
//
//    fun loadCharacters() {
//        viewModelScope.launch {
//            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
//
//            getCharactersUseCase().fold(
//                onSuccess = { characters ->
//                    _uiState.value = _uiState.value.copy(
//                        characters = characters,
//                        isLoading = false
//                    )
//                },
//                onFailure = { exception ->
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        errorMessage = exception.message ?: "Error desconocido"
//                    )
//                }
//            )
//        }
//    }
//}