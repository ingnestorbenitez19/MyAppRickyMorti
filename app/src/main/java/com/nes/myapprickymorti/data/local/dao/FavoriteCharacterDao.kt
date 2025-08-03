package com.nes.myapprickymorti.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nes.myapprickymorti.data.local.entity.FavoriteCharacterEntity


@Dao
interface FavoriteCharacterDao {
    @Query("SELECT * FROM favorite_characters")
    suspend fun getAllFavorites(): List<FavoriteCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteCharacterEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteCharacterEntity)
}

