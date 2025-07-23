package com.nes.myapprickymorti.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.presentation.screens.posts.components.GetPosts


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController) {

    Scaffold(
        content = {
            GetPosts()
        }
    )

}

