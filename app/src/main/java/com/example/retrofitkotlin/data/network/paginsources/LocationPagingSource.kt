package com.example.retrofitkotlin.data.network.paginsources

import com.example.retrofitkotlin.common.base.BasePagingSource
import com.example.retrofitkotlin.data.network.apiservice.LocationApiService
import com.example.retrofitkotlin.data.network.dtos.LocationModel

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationModel>({ position ->
    service.fetchLocations(position)
})