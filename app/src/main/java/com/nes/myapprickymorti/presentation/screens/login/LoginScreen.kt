package com.nes.myapprickymorti.presentation.screens.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import com.nes.myapprickymorti.presentation.screens.login.components.Login
import com.nes.myapprickymorti.presentation.screens.login.components.LoginBottomBar
import com.nes.myapprickymorti.presentation.screens.login.components.LoginContent


@Composable
fun LoginScreen(navController: NavHostController) {


    Scaffold(
        topBar = {},
        content = { LoginContent(navController, it) },
        bottomBar = { LoginBottomBar(navController) }
    )

    Login(navController = navController)

}

