package com.example.retrofitkotlin.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.retrofitkotlin.common.base.BaseRepository
import com.example.retrofitkotlin.data.network.apiservice.LocationApiService
import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.data.network.paginsources.LocationPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository(

    private val service: LocationApiService
) : BaseRepository() {

    fun fetchLocations(): Flow<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }

}