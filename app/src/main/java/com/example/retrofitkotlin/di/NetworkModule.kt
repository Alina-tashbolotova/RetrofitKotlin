package com.example.retrofitkotlin.di

import com.example.retrofitkotlin.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiService() = retrofitClient.characterRetrofitClient()

    @Provides
    @Singleton
    fun provideEpisodeApiService() = retrofitClient.episodeRetrofitClient()

    @Provides
    @Singleton
    fun provideLocationApiService() = retrofitClient.locationRetrofitClient()
}