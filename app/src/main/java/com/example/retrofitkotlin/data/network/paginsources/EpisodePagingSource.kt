package com.example.retrofitkotlin.data.network.paginsources

import com.example.retrofitkotlin.common.base.BasePagingSource
import com.example.retrofitkotlin.data.network.apiservice.EpisodeApiService
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<EpisodeModel>({ position ->
    service.fetchEpisodes(position)
})