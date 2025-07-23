package com.nes.myapprickymorti.domain.use_cases.users




import com.nes.myapprickymorti.domain.model.User
import com.nes.myapprickymorti.domain.repository.UsersRepository
import javax.inject.Inject


class Create @Inject constructor(private val userRepository: UsersRepository){

    suspend operator fun invoke(user: User) = userRepository.create(user)

}
