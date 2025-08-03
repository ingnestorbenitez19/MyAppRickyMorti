package com.nes.myapprickymorti.presentation.screens.characters

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.presentation.screens.characters.components.FiltersRow
import com.nes.myapprickymorti.presentation.screens.characters.components.GetCharacters
import com.nes.myapprickymorti.presentation.screens.characters.components.SearchTopAppBar
import com.nes.myapprickymorti.presentation.screens.posts.components.GetPosts


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharactersScreen(navController: NavHostController, viewModel: CharacterViewModel = hiltViewModel()) {

    Log.i("CharactersScreen", "CharactersScreen")

    val searchText by viewModel.searchText.collectAsState()
    var isSearching by remember { mutableStateOf(false) }

    val statusOptions = listOf("Alive", "Dead", "unknown")
    val speciesOptions by viewModel.filteredCharacters.collectAsState()
    val allSpecies = speciesOptions.map { it.species }.distinct().filter { it.isNotBlank() }

    val selectedStatus by viewModel.statusFilter.collectAsState()
    val selectedSpecies by viewModel.speciesFilter.collectAsState()

    Scaffold(
        topBar = {
            //TopAppBar(title = { Text("Rick and Morty") })
            SearchTopAppBar(
                searchText = searchText,
                onSearchTextChange = { viewModel.onSearchTextChange(it) },
                isSearching = isSearching,
                onSearchToggle = { isSearching = !isSearching },
                onSearch = {
                    Log.d("Search", "Buscando: $searchText")
                }
            )
        },
        content = {
            Column {
                FiltersRow(
                    statusOptions = statusOptions,
                    speciesOptions = allSpecies,
                    selectedStatus = selectedStatus,
                    selectedSpecies = selectedSpecies,
                    onStatusChange = { viewModel.onStatusFilterChange(it) },
                    onSpeciesChange = { viewModel.onSpeciesFilterChange(it) }
                )
                GetCharacters(viewModel = viewModel, navController = navController)
            }
        }
    )

}


