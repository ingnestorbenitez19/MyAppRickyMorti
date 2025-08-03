package com.nes.myapprickymorti.domain.use_cases.auth



data class AuthUseCases(

    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val signUp: SignUp

)

