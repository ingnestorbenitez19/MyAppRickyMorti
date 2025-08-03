package com.nes.myapprickymorti.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nes.myapprickymorti.data.local.entity.ReadEpisodeEntity


@Dao
interface ReadEpisodeDao {
    @Query("SELECT * FROM read_episodes")
    suspend fun getAllReadEpisodes(): List<ReadEpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markEpisodeAsRead(episode: ReadEpisodeEntity)

    @Delete
    suspend fun unmarkEpisodeAsRead(episode: ReadEpisodeEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM read_episodes WHERE episodeId = :episodeId)")
    suspend fun isEpisodeRead(episodeId: Int): Boolean
}


