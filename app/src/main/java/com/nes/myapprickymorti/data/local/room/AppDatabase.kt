package com.nes.myapprickymorti.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nes.myapprickymorti.data.local.dao.FavoriteCharacterDao
import com.nes.myapprickymorti.data.local.dao.ReadEpisodeDao
import com.nes.myapprickymorti.data.local.entity.FavoriteCharacterEntity
import com.nes.myapprickymorti.data.local.entity.ReadEpisodeEntity


@Database(entities = [FavoriteCharacterEntity::class, ReadEpisodeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
    abstract fun readEpisodeDao(): ReadEpisodeDao
}