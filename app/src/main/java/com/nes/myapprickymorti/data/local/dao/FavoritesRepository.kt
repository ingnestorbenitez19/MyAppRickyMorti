package com.nes.myapprickymorti.data.local.dao

import com.nes.myapprickymorti.data.local.entity.FavoriteCharacterEntity
import javax.inject.Inject


class FavoritesRepository (private val dao: FavoriteCharacterDao) {

    suspend fun getFavoriteIds(): List<Int> = dao.getAllFavorites().map { it.characterId }
    suspend fun addFavorite(id: Int) = dao.addFavorite(FavoriteCharacterEntity(id))
    suspend fun removeFavorite(id: Int) = dao.removeFavorite(FavoriteCharacterEntity(id))
}


