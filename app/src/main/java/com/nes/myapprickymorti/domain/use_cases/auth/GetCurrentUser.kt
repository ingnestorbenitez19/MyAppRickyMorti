package com.nes.myapprickymorti.domain.use_cases.auth



import com.nes.myapprickymorti.domain.repository.AuthRepository
import javax.inject.Inject


class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser

}
