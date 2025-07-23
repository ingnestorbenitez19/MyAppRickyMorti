package com.nes.myapprickymorti.presentation.screens.signup.components



import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.navigation.Graph
import com.nes.myapprickymorti.presentation.screens.signup.SignupViewModel


@Composable
fun SignUp(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()){

    when(val signUpResponse = viewModel.signupResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.createUser()
                navController.popBackStack(Graph.AUTHENTICATION, true)
                navController.navigate(route = Graph.HOME) //{
//                    popUpTo(AuthScreen.Signup.route){
//                        inclusive = true
//                    }
//                }
            }
        }is Response.Failure -> {
        Toast.makeText(LocalContext.current, signUpResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
    }
        else -> {}
    }

}
