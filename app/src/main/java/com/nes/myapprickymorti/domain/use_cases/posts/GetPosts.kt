package com.nes.myapprickymorti.domain.use_cases.posts



import com.nes.myapprickymorti.domain.repository.PostsRepository
import javax.inject.Inject


class GetPosts @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke() = repository.getPost()

}
