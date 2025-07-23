package com.nes.myapprickymorti.domain.use_cases.auth



import com.nes.myapprickymorti.domain.model.User
import com.nes.myapprickymorti.domain.repository.AuthRepository
import javax.inject.Inject


class SignUp @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signUp(user)

}
