package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitkotlin.data.network.apiservice.EpisodeApiService
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.network.paginsources.EpisodePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(

    private val service: EpisodeApiService
) {

    fun fetchEpisodes(): LiveData<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }

    fun fetchEpisode(id: Int): MutableLiveData<EpisodeModel> {
        val data: MutableLiveData<EpisodeModel> = MutableLiveData()
        service.fetchEpisode(id).enqueue(object : Callback<EpisodeModel> {
            override fun onResponse(call: Call<EpisodeModel>, response: Response<EpisodeModel>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<EpisodeModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

}