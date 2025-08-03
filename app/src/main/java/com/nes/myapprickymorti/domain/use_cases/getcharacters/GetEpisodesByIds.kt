package com.nes.myapprickymorti.domain.use_cases.getcharacters

import com.nes.myapprickymorti.data.remote.dto.EpisodeDTO
import com.nes.myapprickymorti.domain.repository.CharacterRepository
import javax.inject.Inject


class GetEpisodesByIds @Inject constructor(private val repository: CharacterRepository){

    suspend operator fun invoke(ids: List<Int>): List<EpisodeDTO> = repository.getEpisodesByIds(ids)

}