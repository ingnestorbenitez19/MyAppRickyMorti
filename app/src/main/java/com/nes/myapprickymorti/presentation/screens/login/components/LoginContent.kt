package com.nes.myapprickymorti.presentation.screens.login.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nes.myapprickymorti.R
import com.nes.myapprickymorti.presentation.components.DefaultButton
import com.nes.myapprickymorti.presentation.components.DefaultTextField
import com.nes.myapprickymorti.presentation.screens.login.LoginViewModel
import com.nes.myapprickymorti.ui.theme.Darkgray500
import com.nes.myapprickymorti.ui.theme.Red500


@Composable
fun LoginContent(navaController: NavHostController, paddingValues: PaddingValues, loginViewModel: LoginViewModel = hiltViewModel()) {


//    val loginFlow = loginViewModel.loginFlow.collectAsState()
    val state = loginViewModel.state

    Box(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Red500)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Image(
                    modifier = Modifier.height(130.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control de xbox 360"
                )
                Text("FIREBASE MVVM")

            }
        }
    }

//    var email by remember{
//        mutableStateOf("")
//    }
//
//    var password by remember{
//        mutableStateOf("")
//    }

    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
        backgroundColor = Darkgray500
    ){

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            Text(
                modifier = Modifier.padding(
                    top = 40.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
                text = "LOGIN",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Por favor inicia sesion para continuar",
                fontSize = 12.sp,
                color = Color.Gray
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 25.dp),
                value = state.email,
                onValueChange = { loginViewModel.onEmailInpout(it) },
                validateField = { loginViewModel.validateEmail() },
                label = "Correo Electronico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = loginViewModel.emailErrMsg
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = state.password,
                onValueChange = { loginViewModel.onPasswordInput(it) },
                validateField = { loginViewModel.validatePassword() },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                hideText = true,
                errorMsg = loginViewModel.passwordErrMsg
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                text = "INICIAR SESION",
                onClick = {
/*                    Log.d("LoginContent", "Email: ${loginViewModel.email.value}")
                    Log.d("LoginContent", "Password: ${loginViewModel.password.value}")*/
                    loginViewModel.login()
                },
                enabled = loginViewModel.isEnabledLoginButton
            )

            //Login Hardcode
//            DefaultButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 40.dp),
//                text = "INICIAR SESION HARD",
//                onClick = {
//                    loginViewModel.loginHardcode()
//                },
//                enabled = loginViewModel.isEnabledLoginButton
//            )

        }
    }

}



@Composable
fun BoxHeader() {

}


@Composable
fun CardForm(loginViewModel: LoginViewModel){

}
