package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitkotlin.common.base.BaseRepository
import com.example.retrofitkotlin.data.network.apiservice.EpisodeApiService
import com.example.retrofitkotlin.data.network.apiservice.LocationApiService
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.data.network.paginsources.EpisodePagingSource
import com.example.retrofitkotlin.data.network.paginsources.LocationPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(

    private val service: LocationApiService
):BaseRepository() {

    fun fetchLocations(): LiveData<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }

}