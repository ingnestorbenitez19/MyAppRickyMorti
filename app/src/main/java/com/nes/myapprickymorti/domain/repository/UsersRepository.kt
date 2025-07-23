package com.nes.myapprickymorti.domain.repository

import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File


interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    suspend fun saveImage(file: File): Response<String>
    fun getUserById(id: String): Flow<User>

}
