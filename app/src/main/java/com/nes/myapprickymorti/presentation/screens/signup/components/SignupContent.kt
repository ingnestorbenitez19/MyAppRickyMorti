package com.nes.myapprickymorti.presentation.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.nes.myapprickymorti.R
import com.nes.myapprickymorti.ui.theme.Darkgray500
import com.nes.myapprickymorti.ui.theme.Red500

import androidx.hilt.navigation.compose.hiltViewModel
import com.nes.myapprickymorti.presentation.screens.signup.SignupViewModel
import androidx.navigation.NavController
import com.nes.myapprickymorti.presentation.components.DefaultTextField
import com.nes.myapprickymorti.presentation.components.DefaultButton


@Composable
fun SignupContent(navController: NavController, signupViewModel: SignupViewModel = hiltViewModel()) {


//    val signUpFlow = signupViewModel.signUpFlow.collectAsState()
    val state = signupViewModel.state


    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Red500),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    modifier = Modifier.height(90.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Imagen de usuario"
                )
            }
        }

        Card(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 140.dp),
            backgroundColor = Darkgray500
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                Text(
                    modifier = Modifier.padding(
                        top = 24.dp,
                        bottom = 0.dp,
                        start = 0.dp,
                        end = 0.dp
                    ),
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar"

                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChange = { signupViewModel.onUsernameInput(it) },
                    label = "Nombre de Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = signupViewModel.usernameErrMsg,
                    validateField = { signupViewModel.validateUsername() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { signupViewModel.onEmailInput(it) },
                    label = "Correo Electronico",
                    icon = Icons.Default.Person,
                    errorMsg = signupViewModel.emailErrMsg,
                    validateField = { signupViewModel.validateEmail() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { signupViewModel.onPasswordInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Person,
                    errorMsg = signupViewModel.passwordErrMsg,
                    validateField = { signupViewModel.validatePassword() }
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmPassword,
                    onValueChange = { signupViewModel.onConfirmPasswordInput(it) },
                    label = "Confirmar Contraseña",
                    icon = Icons.Default.Person,
                    errorMsg = signupViewModel.confirmPasswordErrMsg,
                    validateField = { signupViewModel.validateConfirmPassword() }
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "REGISTRARSE",
                    onClick = { signupViewModel.onSignup() },
                    enabled = signupViewModel.isEnabledLoginButton
                )
            }
        }
    }

}
