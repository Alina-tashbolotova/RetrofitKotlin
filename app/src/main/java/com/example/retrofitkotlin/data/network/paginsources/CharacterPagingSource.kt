package com.example.retrofitkotlin.data.network.paginsources

import com.example.retrofitkotlin.common.base.BasePagingSource
import com.example.retrofitkotlin.data.network.apiservice.CharacterApiService
import com.example.retrofitkotlin.data.network.dtos.CharacterModel

class CharacterPagingSource(
    private val service: CharacterApiService
) : BasePagingSource<CharacterModel>({ position ->
    service.fetchCharacters(position)
})