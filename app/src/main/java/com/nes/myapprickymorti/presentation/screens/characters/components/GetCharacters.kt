package com.nes.myapprickymorti.presentation.screens.characters.components



import android.widget.Toast
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.characters.CharacterViewModel
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO

import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController


@Composable
fun GetCharacters(viewModel: CharacterViewModel = hiltViewModel(), navController: NavHostController) {

    val response by viewModel.characters.collectAsState()
    val filteredCharacters by viewModel.filteredCharacters.collectAsState()
    val listState = rememberLazyListState()

    when(response){

        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {

            CharactersContent(
                characters = filteredCharacters,
                onLoadMore = { viewModel.loadCharacters() },
                listState = listState,
                onItemClick = { character ->
                    navController.navigate("character_detail/${character.id}")
                }
            )

        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}
