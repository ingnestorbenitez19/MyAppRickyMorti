package com.nes.myapprickymorti.presentation.screens.login



import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.nes.myapprickymorti.domain.model.LoginUser
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.use_cases.auth.AuthUseCases


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {


    //State form
    var state by mutableStateOf(LoginState())
        private set

    var isEmailValid by mutableStateOf(false)
        private set

    var emailErrMsg by mutableStateOf("")
        private set


    var isPasswordValid by mutableStateOf(false)
        private set

    var passwordErrMsg by mutableStateOf("")
        private set

    // Variable para indicar si es login hardcodeado
    var isHardcodedLogin by mutableStateOf(false)
        private set


    var isEnabledLoginButton = false


    /*    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
        val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow*/


    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)


    //obtiene el usuario actual
    val currentUser = authUseCases.getCurrentUser()

    //envia un Login Success
    init{
        if(currentUser != null){
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInpout(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }


    fun login() = viewModelScope.launch{

        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result

    }


    private fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid && isPasswordValid
        Log.i("isEnabledLoginButton", isEnabledLoginButton.toString())
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }
        else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }

        enabledLoginButton()

    }


    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }
        else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enabledLoginButton()

    }

    //Hardcode Login
    var loginResponseHardcode by mutableStateOf<Response<LoginUser>?>(null)

    fun loginHardcode() = viewModelScope.launch{

        loginResponseHardcode = Response.Loading
        val result = authUseCases.loginHardcode(state.email, state.password)
        loginResponseHardcode = result

    }


}
