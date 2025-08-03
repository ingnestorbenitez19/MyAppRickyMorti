package com.nes.myapprickymorti.presentation.screens.favourites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.nes.myapprickymorti.data.local.dao.FavoritesRepository
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.domain.repository.CharacterRepository
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    //private val characterRepository: CharacterRepository,
    private val charactersResponse: GetCharactersUseCase
) : ViewModel() {

    var favoriteCharacters by mutableStateOf<List<CharacterDTO>>(emptyList())
        private set

    fun loadFavorites() {
        viewModelScope.launch {
            val ids = favoritesRepository.getFavoriteIds()
            if (ids.isNotEmpty()) {
                favoriteCharacters = charactersResponse.getCharactersByIds(ids)
            } else {
                favoriteCharacters = emptyList()
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            val ids = favoritesRepository.getFavoriteIds()
            if (ids.contains(id)) {
                favoritesRepository.removeFavorite(id)
            } else {
                favoritesRepository.addFavorite(id)
            }
            loadFavorites()
        }
    }

    suspend fun isFavorite(id: Int): Boolean {
        return favoritesRepository.getFavoriteIds().contains(id)
    }
}