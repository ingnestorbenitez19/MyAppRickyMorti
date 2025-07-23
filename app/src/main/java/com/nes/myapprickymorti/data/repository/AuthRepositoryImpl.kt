package com.nes.myapprickymorti.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nes.myapprickymorti.domain.model.LoginUser
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.model.User
import com.nes.myapprickymorti.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.delay
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {


        return try {

            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {

        return try{

            val result = firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
            Response.Success(result.user!!)

        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override fun logout(){
        firebaseAuth.signOut()
    }

    override suspend fun loginHardcode(
        email: String,
        password: String
    ): Response<LoginUser> {

        return try {

            delay(1000)

            // Credenciales hardcodeadas
            if (email == "test@example.com" && password == "123456") {
                val user = LoginUser(
                    id = "hardcoded_user_123",
                    email = email,
                    name = "Usuario de Prueba"
                )
                Response.Success(user)
            } else {
                Response.Failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Response.Failure(e)
        }

    }

}
