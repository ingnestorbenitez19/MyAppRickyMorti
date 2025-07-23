package com.nes.myapprickymorti.presentation.screens.signup


import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController

import com.nes.myapprickymorti.presentation.components.DefaultTopBar
import com.nes.myapprickymorti.presentation.screens.profile.components.SignUp
import com.nes.myapprickymorti.presentation.screens.signup.components.SignupContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo Usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            //Text(text = "SignupScreen")
            SignupContent(navController)
        },
        bottomBar = {}
    )
    SignUp(navController = navController)

}
