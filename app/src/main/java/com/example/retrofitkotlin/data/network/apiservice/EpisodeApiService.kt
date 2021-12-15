package com.example.retrofitkotlin.data.network.apiservice

import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.network.dtos.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int) : RickAndMortyResponse<EpisodeModel>

    @GET("episode/{id}")
    fun fetchEpisode(@Path("id") id: Int) : Call<EpisodeModel>
}