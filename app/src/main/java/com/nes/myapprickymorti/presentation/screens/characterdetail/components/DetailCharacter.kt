package com.nes.myapprickymorti.presentation.screens.characterdetail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.characters.CharacterViewModel


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
            DetailCharacterContent(character)
        }
        is Response.Failure -> {
            Text(
                text = "No se pudo cargar el personaje",
                modifier = Modifier.padding(16.dp)
            )
        }
    }


}