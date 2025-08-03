package com.nes.myapprickymorti.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nes.myapprickymorti.data.remote.RetrofitInstance.api
import com.nes.myapprickymorti.data.remote.dto.CharacterApiResponse
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO
import com.nes.myapprickymorti.data.remote.dto.CharacterResponseDTO
import com.nes.myapprickymorti.data.remote.dto.CharacterResult
import com.nes.myapprickymorti.data.remote.dto.EpisodeDTO
import com.nes.myapprickymorti.data.remote.dto.EpisodeResult
import com.nes.myapprickymorti.data.repository.CharacterPagingSource
import com.nes.myapprickymorti.domain.model.Response
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {

    suspend fun getCharacters(page: Int): CharacterResponseDTO

    suspend fun getCharacterById(id: Int): CharacterDTO

    suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeDTO>

    suspend fun getCharactersByIds(ids: List<Int>): List<CharacterDTO>

}