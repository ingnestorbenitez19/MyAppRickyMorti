package com.nes.myapprickymorti.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nes.myapprickymorti.data.remote.dto.CharacterApiResponse
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("api/character")
    suspend fun getCharacters(): CharacterApiResponse

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterApiResponse

    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterResult

    @GET("api/episode/{ids}")
    suspend fun getEpisodesByIds(@Path("ids") ids: String): Any

}

object RetrofitInstance {
    private val json = Json { ignoreUnknownKeys = true }

    val api: RickAndMortyApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RickAndMortyApiService::class.java)
    }
}

