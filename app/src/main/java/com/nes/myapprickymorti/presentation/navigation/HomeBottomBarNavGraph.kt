package com.nes.myapprickymorti.presentation.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.nes.myapprickymorti.presentation.screens.characters.CharactersScreen
import com.nes.myapprickymorti.presentation.screens.characterdetail.DetailCharacterScreen
import com.nes.myapprickymorti.presentation.screens.posts.PostsScreen


@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Characters.route
    ){

        composable(route = HomeBottomBarScreen.Characters.route) {
            CharactersScreen(navController)
        }

        composable(route = "character_detail/{characterId}"){
            backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            characterId?.let {
                DetailCharacterScreen(characterId = it, navController = navController)
            }
        }

        composable(route = HomeBottomBarScreen.Posts.route) {
            PostsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.MyPosts.route) {
            PostsScreen(navController)
        }


//        composable(route = HomeBottomBarScreen.MyPosts.route) {
//            MyPostsScreen(navController)
//        }

    }
}

sealed class HomeBottomBarScreen(

    val route: String,
    var title: String,
    val icon: ImageVector

){

    object Posts: HomeBottomBarScreen(
        route = "posts",
        title = "Posts",
        icon = Icons.Outlined.List
    )
    object MyPosts: HomeBottomBarScreen(
        route = "my_posts",
        title = "Mis Posts",
        icon = Icons.Outlined.FormatListBulleted
    )
    object Characters: HomeBottomBarScreen(
        route = "characters",
        title = "Personajes",
        icon = Icons.Outlined.ViewList
    )



//    object Profile: HomeBottomBarScreen(
//        route = "profile",
//        title = "Perfil",
//        icon = Icons.Default.Person
//    )


}
