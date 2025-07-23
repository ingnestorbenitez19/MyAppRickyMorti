package com.nes.myapprickymorti.domain.use_cases.users



import com.nes.myapprickymorti.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val userRepository: UsersRepository) {

    operator fun invoke(id: String) = userRepository.getUserById(id)

}


