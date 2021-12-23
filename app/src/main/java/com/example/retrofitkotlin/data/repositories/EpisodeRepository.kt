package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitkotlin.common.base.BaseRepository
import com.example.retrofitkotlin.data.network.apiservice.EpisodeApiService
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.network.paginsources.EpisodePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository @Inject constructor(

    private val service: EpisodeApiService
) : BaseRepository() {

    fun fetchEpisodes(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).flow
    }

    fun fetchEpisode(id: Int) = doRequest{
        service.fetchEpisode(id)
    }

}