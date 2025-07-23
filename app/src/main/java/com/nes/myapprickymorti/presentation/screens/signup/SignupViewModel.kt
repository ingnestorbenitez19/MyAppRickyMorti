package com.nes.myapprickymorti.presentation.screens.signup



import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.model.User
import com.nes.myapprickymorti.domain.use_cases.auth.AuthUseCases
import com.nes.myapprickymorti.domain.use_cases.users.UsersUseCases


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {


    //State form
    var state by mutableStateOf(SignupState())
        private set


    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set


    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set


    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set


    var isconfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set


    var isEnabledLoginButton = false


//    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
//    val signUpFlow: StateFlow<Response<FirebaseUser>?> = _signupFlow

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set


    var user = User()


    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }



    fun onSignup() {

        user.username = state.username
        user.email = state.email
        user.password = state.password
        signup(user)
    }


    fun createUser() = viewModelScope.launch {

        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)

    }




    private fun signup(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUseCases.signUp(user)
        signupResponse = result
    }


    private fun enabledLoginButton() {
        isEnabledLoginButton = isEmailValid &&
                isPasswordValid &&
                isUsernameValid &&
                isconfirmPassword
        Log.i("isEnabledLoginButton", isEnabledLoginButton.toString())
    }


    fun validateUsername() {

        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = ""
        } else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }

        enabledLoginButton()

    }


    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }

        enabledLoginButton()

    }


    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enabledLoginButton()

    }


    fun validateConfirmPassword() {

        if (state.password == state.confirmPassword) {
            isconfirmPassword = true
            confirmPasswordErrMsg = ""
        } else {
            isconfirmPassword = true
            confirmPasswordErrMsg = "Las contraseñas no coinciden"
        }

        enabledLoginButton()

    }

}
