package com.nes.myapprickymorti.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color


@Composable
fun CharacterDetailScreen(characterId: Int, viewModel: CharacterDetailViewModel = hiltViewModel()) {

    val character = viewModel.character
    val episodes = viewModel.episodes
    val isLoading = viewModel.isLoading
    val error = viewModel.error


    LaunchedEffect(characterId) {
        viewModel.loadCharacter(characterId)
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del personaje") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                character != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            Image(
                                painter = rememberImagePainter(character.image),
                                contentDescription = character.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = character.name, style = MaterialTheme.typography.h5, color = Color.Blue)
                            Text(text = "Género: ${character.gender}", color = Color.Blue)
                            Text(text = "Especie: ${character.species}", color = Color.Blue)
                            Text(text = "Estado: ${character.status}", color = Color.Blue)
                            Text(text = "Ubicación: ${character.location.name}", color = Color.Blue)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Episodios donde aparece:",
                                style = MaterialTheme.typography.h6,
                                color = Color.Blue
                            )
                        }
                        items(episodes) { episode ->
                            Text(
                                text = "${episode.episode} - ${episode.name}",
                                style = MaterialTheme.typography.body1,
                                color = Color.Blue,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}