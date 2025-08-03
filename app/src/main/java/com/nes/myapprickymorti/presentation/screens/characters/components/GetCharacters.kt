package com.nes.myapprickymorti.presentation.screens.characters.components



import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.characters.CharacterViewModel
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            //Toast.makeText(LocalContext.current, "Error desconocido", Toast.LENGTH_LONG).show()
            ErrorContent(onRetry = { viewModel.loadCharacters() })
        }
        else -> {}
    }

}



@Composable
fun ErrorContent(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Ocurrió un error al cargar los personajes.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text("Reintentar")
            }
        }
    }
}
