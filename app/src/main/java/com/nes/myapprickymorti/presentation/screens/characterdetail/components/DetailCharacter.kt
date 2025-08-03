package com.nes.myapprickymorti.presentation.screens.characterdetail.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.data.remote.dto.EpisodeDTO
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.characters.CharacterViewModel
import com.nes.myapprickymorti.presentation.screens.episodes.ReadEpisodeViewModel


@Composable
fun DetailCharacter(
    characterId: Int,
    viewModel: CharacterViewModel
){

    var characterDetail by remember { mutableStateOf<Response<CharacterDTO>>(Response.Loading) }

    LaunchedEffect(characterId) {
        characterDetail = Response.Loading
        try {
            val character = viewModel.getCharacterDetailFromApi(characterId)
            characterDetail = Response.Success(character)
        } catch (e: Exception) {
            characterDetail = Response.Failure(e)
        }
    }

    when (characterDetail) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> {
            val character = (characterDetail as Response.Success<CharacterDTO>).data

            LaunchedEffect(character.id) {
                Log.i("Detalle", "Episodios del personaje: ${character.episode}")
                viewModel.loadEpisodes(character.episode)
            }

            val episodesState by viewModel.episodes.collectAsState()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    DetailCharacterContent(character)
                }
                item {
                    when (episodesState) {
                        is Response.Loading -> ProgressBar()
                        is Response.Success -> {
                            val episodes = (episodesState as Response.Success<List<EpisodeDTO>>).data
                            EpisodesList(episodes)
                        }
                        is Response.Failure -> {
                            Text(
                                "No se pudieron cargar los episodios",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
        is Response.Failure -> {
            Text(
                text = "No se pudo cargar el personaje",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Composable
fun EpisodesList(episodes: List<EpisodeDTO>,
                 readEpisodeViewModel: ReadEpisodeViewModel = hiltViewModel()) {


    val readEpisodes by readEpisodeViewModel.readEpisodes

    LaunchedEffect(Unit) {
        readEpisodeViewModel.loadReadEpisodes()
    }

    Column(
        modifier = Modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Episodios:", style = MaterialTheme.typography.h6)
        if (episodes.isEmpty()) {
            Text("No hay episodios para mostrar.")
        } else {
//            episodes.forEach { episode ->
//                Text("- ${episode.episode}: ${episode.name}")
//            }

            episodes.forEach { episode ->
                val isRead = readEpisodes.contains(episode.id)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { readEpisodeViewModel.toggleRead(episode.id) }
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = isRead,
                        onCheckedChange = { readEpisodeViewModel.toggleRead(episode.id) }
                    )
                    Text("- ${episode.episode}: ${episode.name}")
                }
            }
        }
    }
}

