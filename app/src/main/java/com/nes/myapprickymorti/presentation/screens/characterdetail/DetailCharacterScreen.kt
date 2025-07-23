package com.nes.myapprickymorti.presentation.screens.characterdetail



import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.presentation.screens.characters.CharacterViewModel
import com.nes.myapprickymorti.presentation.screens.characterdetail.components.DetailCharacter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailCharacterScreen(characterId: Int,
                          navController: NavHostController,
                          viewModel: CharacterViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Personaje") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        content = {
            DetailCharacter(characterId = characterId, viewModel = viewModel)
        }
    )

}