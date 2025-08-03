package com.nes.myapprickymorti.presentation.screens.login.components


import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar

import com.nes.myapprickymorti.presentation.navigation.Graph
import com.nes.myapprickymorti.presentation.screens.login.LoginViewModel


@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){


    when(val loginResponse = viewModel.loginResponse){

        Response.Loading -> {
            Log.d("Login", "Cargando...")
            ProgressBar()
        }
        is Response.Success -> {
            Log.d("Login", "Login exitoso")
            LaunchedEffect(Unit){

                navController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION){
                        inclusive = true
                    }
                }
            }
            Toast.makeText(LocalContext.current, "Usuario logeado", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            val context = LocalContext.current
            val exception = loginResponse.exception
            val errorMsg = exception?.message ?: exception?.toString() ?: "Error"
            Log.d("Login", "Login fallido: ${loginResponse.exception}")
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
        }

        null -> {
            Log.d("Login", "Estado inicial: loginResponse es null")
        }
    }
}
