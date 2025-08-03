package com.nes.myapprickymorti.presentation.screens.characterdetail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import coil.compose.rememberImagePainter
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.presentation.screens.favourites.FavoritesViewModel


@Composable
fun DetailCharacterContent(
    character: CharacterDTO,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(character.id) {
        isFavorite = viewModel.isFavorite(character.id)
    }


    IconButton(onClick = {
        viewModel.toggleFavorite(character.id)
        isFavorite = !isFavorite
    }) {
        if (isFavorite) {
            Icon(Icons.Filled.Favorite, contentDescription = "Quitar de favoritos", tint = Color.Red)
        } else {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Agregar a favoritos")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
        Text(text = character.name, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Status: ${character.status}")
        Text(text = "Species: ${character.species}")
        if (character.type.isNotBlank()) {
            Text(text = "Type: ${character.type}")
        }
        Text(text = "Gender: ${character.gender}")
        Text(text = "Numero Episodios: ${character.episode.size}")

    }

}