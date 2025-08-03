package com.nes.myapprickymorti.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "read_episodes")
data class ReadEpisodeEntity(
    @PrimaryKey val episodeId: Int
)