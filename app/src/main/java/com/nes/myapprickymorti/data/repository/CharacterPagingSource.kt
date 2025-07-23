package com.nes.myapprickymorti.data.repository

import android.graphics.pdf.LoadParams
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.remote.dto.CharacterDTO




class CharacterPagingSource(
    private val api: RickAndMortyApiService
) : PagingSource<Int, CharacterDTO>() {


    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {

        Log.i("CharacterPagingSource", "Cargando página ${params.key}")

        return try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)
            val characters = response.results.map { character ->
                CharacterDTO(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    image = character.image
                )
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}