package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitkotlin.data.network.apiservice.CharacterApiService
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.data.network.paginsources.CharacterPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(

    private val service: CharacterApiService
) {

    fun fetchCharacters(): LiveData<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }

    fun characterRepository(id: Int): MutableLiveData<CharacterModel> {
        val data: MutableLiveData<CharacterModel> = MutableLiveData()
        service.fetchCharacter(id).enqueue(object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }


}