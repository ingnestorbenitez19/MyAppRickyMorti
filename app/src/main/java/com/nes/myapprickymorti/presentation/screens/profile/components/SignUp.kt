package com.nes.myapprickymorti.presentation.screens.profile.components



import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.signup.SignupViewModel

import com.nes.myapprickymorti.presentation.navigation.Graph


@Composable
fun SignUp(navController: NavHostController, signupViewModel: SignupViewModel = hiltViewModel()) {

    when(val signupResponse = signupViewModel.signupResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){

                signupViewModel.createUser()
                //Elimina todas las pantallas de la pila de navegacion
                navController.popBackStack(Graph.AUTHENTICATION, true)
                navController.navigate(route = Graph.HOME)

            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, signupResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {  }
    }

}


