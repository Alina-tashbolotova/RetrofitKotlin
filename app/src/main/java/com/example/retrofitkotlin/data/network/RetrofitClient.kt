package com.example.retrofitkotlin.data.network

import com.example.retrofitkotlin.constants.Constants
import com.example.retrofitkotlin.data.network.apiservice.CharacterApiService
import com.example.retrofitkotlin.data.network.apiservice.EpisodeApiService
import com.example.retrofitkotlin.data.network.apiservice.LocationApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor()!!)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val provideRetrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun characterRetrofitClient(): CharacterApiService {
        return provideRetrofitClient.create(CharacterApiService::class.java)
    }

    fun episodeRetrofitClient(): EpisodeApiService {
        return provideRetrofitClient.create(EpisodeApiService::class.java)
    }

    fun locationRetrofitClient(): LocationApiService {
        return provideRetrofitClient.create(LocationApiService::class.java)
    }
}