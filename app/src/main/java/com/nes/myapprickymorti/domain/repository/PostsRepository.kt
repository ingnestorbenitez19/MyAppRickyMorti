package com.nes.myapprickymorti.domain.repository

import com.nes.myapprickymorti.domain.model.Post
import com.nes.myapprickymorti.domain.model.Response
import kotlinx.coroutines.flow.Flow


interface PostsRepository {

    fun getPost(): Flow<Response<List<Post>>>

}
