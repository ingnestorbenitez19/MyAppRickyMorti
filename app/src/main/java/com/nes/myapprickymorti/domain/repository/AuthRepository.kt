package com.nes.myapprickymorti.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.nes.myapprickymorti.domain.model.Response


interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Response<FirebaseUser>

}