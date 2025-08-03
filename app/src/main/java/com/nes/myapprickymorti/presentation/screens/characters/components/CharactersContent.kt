package com.nes.myapprickymorti.presentation.screens.characters.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.wear.compose.material.CardDefaults
import coil.compose.AsyncImage
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO

@Composable
fun CharactersContent(characters: List<CharacterDTO>,
                      onLoadMore: () -> Unit,
                      listState: LazyListState,
                      onItemClick: (CharacterDTO) -> Unit){


    if (characters.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No se encontraron personajes.")
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        items(characters) { character ->
            //Text(text = character.name)
            CharacterItem(character, onClick = { onItemClick(character) })
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index == characters.lastIndex
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            onLoadMore()
        }
    }


}





@Composable
fun CharacterItem(character: CharacterDTO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = "Status: ",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.body2
                    )
                }
                Row {
                    Text(
                        text = "Species: ",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = character.species,
                        style = MaterialTheme.typography.body2
                    )
                }
                if (character.type.isNotBlank()) {
                    Row {
                        Text(
                            text = "Type: ",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            text = character.type,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
                Row {
                    Text(
                        text = "Gender: ",
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = character.gender,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}




@Composable
fun FiltersRow(
    statusOptions: List<String>,
    speciesOptions: List<String>,
    selectedStatus: String?,
    selectedSpecies: String?,
    onStatusChange: (String?) -> Unit,
    onSpeciesChange: (String?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DropdownFilter(
            label = "Estado",
            options = statusOptions,
            selectedOption = selectedStatus,
            onOptionSelected = onStatusChange
        )
        DropdownFilter(
            label = "Especie",
            options = speciesOptions,
            selectedOption = selectedSpecies,
            onOptionSelected = onSpeciesChange
        )
    }
}

@Composable
fun DropdownFilter(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedOption ?: label)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                onOptionSelected(null)
                expanded = false
            }) {
                Text("Todos")
            }
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onOptionSelected(option)
                    expanded = false
                }) {
                    Text(option)
                }
            }
        }
    }
}

