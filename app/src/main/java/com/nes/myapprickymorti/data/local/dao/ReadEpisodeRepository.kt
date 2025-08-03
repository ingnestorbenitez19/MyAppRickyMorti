package com.nes.myapprickymorti.data.local.dao

import com.nes.myapprickymorti.data.local.entity.ReadEpisodeEntity
import javax.inject.Inject


class ReadEpisodeRepository (private val dao: ReadEpisodeDao) {

    suspend fun getReadEpisodeIds(): List<Int> = dao.getAllReadEpisodes().map { it.episodeId }
    suspend fun markAsRead(id: Int) = dao.markEpisodeAsRead(ReadEpisodeEntity(id))
    suspend fun unmarkAsRead(id: Int) = dao.unmarkEpisodeAsRead(ReadEpisodeEntity(id))
    suspend fun isRead(id: Int): Boolean = dao.isEpisodeRead(id)
}