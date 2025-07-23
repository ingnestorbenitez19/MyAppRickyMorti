package com.nes.myapprickymorti.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nes.myapprickymorti.presentation.screens.home.HomeScreen

//import com.nes.myapprickymorti.presentation.screens.list.CharacterScreen
//import com.nes.myapprickymorti.presentation.screens.list.CharacterViewModel

@Composable
fun RootNavGraph(navController: NavHostController) {

//    NavHost(
//        navController = navController,
////        route = Graph.ROOT,
//        startDestination = Graph.LIST
//    ) {
//
////        composable(route = Graph.LIST) {
////            val characterViewModel: CharacterViewModel = hiltViewModel()
////            CharacterScreen(viewModel = characterViewModel)
////        }
//
//
//        composable(route = Graph.LIST) {
//            val viewModel: CharacterViewModel = hiltViewModel()
//            CharacterScreen(
//                viewModel = viewModel,
//                onCharacterClick = { characterId ->
//                    navController.navigate("${Graph.DETAIL}/$characterId")
//                }
//            )
//        }
//        composable(
//            route = "${Graph.DETAIL}/{characterId}",
//            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
//            CharacterDetailScreen(characterId = characterId)
//        }
//    }



    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {

        authNavGraph(navController = navController)

        composable(route = Graph.HOME){
            HomeScreen()
        }
    }

}