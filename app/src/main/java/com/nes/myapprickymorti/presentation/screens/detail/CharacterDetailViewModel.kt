package com.nes.myapprickymorti.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult
import com.nes.myapprickymorti.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.mapNotNull

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    var character by mutableStateOf<CharacterResult?>(null)
        private set

    var episodes by mutableStateOf<List<EpisodeResult>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun loadCharacter(characterId: Int) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {

                val result = repository.getCharacterById(characterId)
                result.fold(
                    onSuccess = { characterResult ->
                        character = characterResult
                        val episodeIds = characterResult.episode.mapNotNull { it.substringAfterLast("/").toIntOrNull() }
                        episodes = if (episodeIds.isNotEmpty()) {
                            repository.getEpisodesByIds(episodeIds).getOrDefault(emptyList())
                        } else emptyList()
                    },
                    onFailure = { e ->
                        error = e.message
                    }
                )

            } catch (e: Exception) {
                error = e.message
            }
            isLoading = false
        }
    }
}