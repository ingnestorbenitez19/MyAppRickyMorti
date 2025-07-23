package com.nes.myapprickymorti.domain.use_cases.auth

import com.nes.myapprickymorti.domain.repository.AuthRepository
import javax.inject.Inject

class LoginHardcode @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) = repository.loginHardcode(email, password)

}