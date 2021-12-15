package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
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
) {

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

    fun fetchLocation(id: Int): MutableLiveData<LocationModel> {
        val data: MutableLiveData<LocationModel> = MutableLiveData()
        service.fetchLocation(id).enqueue(object : Callback<LocationModel> {
            override fun onResponse(call: Call<LocationModel>, response: Response<LocationModel>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<LocationModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

}