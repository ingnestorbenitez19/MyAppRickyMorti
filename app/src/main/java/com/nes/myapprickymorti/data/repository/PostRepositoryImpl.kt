package com.nes.myapprickymorti.data.repository




import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.nes.myapprickymorti.core.Constants.POSTS
import com.nes.myapprickymorti.core.Constants.USERS

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.http.POST
import java.io.File
import javax.inject.Inject
import javax.inject.Named


import com.nes.myapprickymorti.domain.model.Post
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.model.User


import com.nes.myapprickymorti.domain.repository.PostsRepository


class PostRepositoryImpl @Inject constructor(

    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference

): PostsRepository{


    override fun getPost(): Flow<Response<List<Post>>> = callbackFlow {

        val snapshotListener = postsRef.addSnapshotListener{ snapshot, e ->

            val postsResponse = if(snapshot != null){
                val posts = snapshot.toObjects(Post::class.java)
                Response.Success(posts)
            }else{
                Response.Failure(e)
            }
            trySend(postsResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

}
