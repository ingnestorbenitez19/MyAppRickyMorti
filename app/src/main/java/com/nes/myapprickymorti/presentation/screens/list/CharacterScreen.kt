package com.nes.myapprickymorti.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import androidx.compose.foundation.clickable


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


//@Composable
//fun CharacterScreen(viewModel: CharacterViewModel, onCharacterClick: (Int) -> Unit) {
//    val uiState by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Rick and Morty Characters") })
//        }
//    ) { padding ->
//        Box(modifier = Modifier.padding(padding)) {
//            when {
//                uiState.isLoading -> {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressIndicator()
//                    }
//                }
//                uiState.errorMessage != null -> {
//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = "Error: ${uiState.errorMessage}",
//                            color = MaterialTheme.colors.error
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        Button(onClick = { viewModel.loadCharacters() }) {
//                            Text("Reintentar")
//                        }
//                    }
//                }
//                else -> {
//                    LazyColumn {
//                        items(uiState.characters) { character ->
//                            CharacterItem(
//                                character = character,
//                                onClick = { onCharacterClick(character.id) }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CharacterItem(character: CharacterResult, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp)
//            .clickable { onClick() },
//        elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = character.name,
//                style = MaterialTheme.typography.h6
//            )
//            Text(
//                text = "ID: ${character.id}",
//                style = MaterialTheme.typography.body2,
//                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
//            )
//        }
//    }
//}