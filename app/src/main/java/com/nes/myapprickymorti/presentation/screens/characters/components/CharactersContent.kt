package com.nes.myapprickymorti.presentation.screens.characters.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CardDefaults
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO

@Composable
fun CharactersContent(characters: List<CharacterDTO>,
                      onLoadMore: () -> Unit,
                      listState: LazyListState,
                      onItemClick: (CharacterDTO) -> Unit){

//    LazyColumn(modifier = Modifier.fillMaxWidth()){
//        items(
//            items = characters
//        ){
//            character -> Text(text = character.name)
//        }
//    }


    //val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        items(characters) { character ->
            //Text(text = character.name)

            CharacterItem(character, onClick = { onItemClick(character) })

        }
    }

    // Detecta si el usuario llegó al final de la lista
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
        Column(modifier = Modifier.padding(16.dp)) {
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




//@Composable
//fun CharacterItem(character: CharacterResult) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
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