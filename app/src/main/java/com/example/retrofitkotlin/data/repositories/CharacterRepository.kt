package com.example.retrofitkotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitkotlin.common.base.BaseRepository
import com.example.retrofitkotlin.data.network.apiservice.CharacterApiService
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.data.network.paginsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(

    private val service: CharacterApiService
) : BaseRepository() {

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

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }

}