package com.nes.myapprickymorti.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.nes.myapprickymorti.domain.model.LoginUser
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.model.User


interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Response<FirebaseUser>

    suspend fun signUp(user: User): Response<FirebaseUser>

    fun logout()

    suspend fun loginHardcode(email: String, password: String): Response<LoginUser>


}