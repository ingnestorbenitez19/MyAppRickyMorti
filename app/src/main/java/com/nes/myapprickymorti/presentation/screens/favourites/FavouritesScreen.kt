package com.nes.myapprickymorti.presentation.screens.favourites

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.presentation.screens.posts.components.GetPosts

import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavouritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

//    Scaffold(
//        content = {
//            GetFavourites()
//        }
//    )


    val favorites by remember { derivedStateOf { viewModel.favoriteCharacters } }

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis Favoritos", style = MaterialTheme.typography.h5, modifier = Modifier.padding(bottom = 16.dp))
        if (favorites.isEmpty()) {
            Text("No tienes personajes favoritos aún.")
        } else {
            LazyColumn {
                items(favorites.size) { index ->
                    val character = favorites[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                navController.navigate("character_detail/${character.id}")
                            },
                        elevation = 4.dp
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            AsyncImage(
                                model = character.image,
                                contentDescription = character.name,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(character.name, style = MaterialTheme.typography.subtitle1)
                                Text("Status: ${character.status}", style = MaterialTheme.typography.body2)
                            }
                        }
                    }
                }
            }
        }
    }

}
