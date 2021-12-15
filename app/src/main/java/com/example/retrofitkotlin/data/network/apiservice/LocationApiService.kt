package com.example.retrofitkotlin.data.network.apiservice

import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.data.network.dtos.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocations(
        @Query("page") page: Int
    ): RickAndMortyResponse<LocationModel>

    @GET("location/{id}")
    fun fetchLocation(@Path("id") id: Int): Call<LocationModel>


}