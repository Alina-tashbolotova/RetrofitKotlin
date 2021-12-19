package com.example.retrofitkotlin.data.network.apiservice

import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.data.network.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int
    ): RickAndMortyResponse<CharacterModel>

    @GET("character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): CharacterModel
}