package com.nes.myapprickymorti.presentation.screens.posts.components


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.presentation.components.ProgressBar
import com.nes.myapprickymorti.presentation.screens.posts.PostViewModel


@Composable
fun GetPosts(viewModel: PostViewModel = hiltViewModel()) {

    when(val response = viewModel.postsResponse){

        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            PostsContent(posts = response.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}
