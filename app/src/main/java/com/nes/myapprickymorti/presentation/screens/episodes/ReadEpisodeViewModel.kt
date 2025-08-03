package com.nes.myapprickymorti.presentation.screens.episodes

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nes.myapprickymorti.data.local.dao.ReadEpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReadEpisodeViewModel @Inject constructor(
    private val repository: ReadEpisodeRepository
) : ViewModel() {

    private val _readEpisodes = mutableStateOf<List<Int>>(emptyList())
    val readEpisodes: State<List<Int>> = _readEpisodes

    fun loadReadEpisodes() {
        viewModelScope.launch {
            _readEpisodes.value = repository.getReadEpisodeIds()
        }
    }

    fun toggleRead(episodeId: Int) {
        viewModelScope.launch {
            if (repository.isRead(episodeId)) {
                repository.unmarkAsRead(episodeId)
            } else {
                repository.markAsRead(episodeId)
            }
            loadReadEpisodes()
        }
    }

    suspend fun isRead(episodeId: Int): Boolean = repository.isRead(episodeId)

}