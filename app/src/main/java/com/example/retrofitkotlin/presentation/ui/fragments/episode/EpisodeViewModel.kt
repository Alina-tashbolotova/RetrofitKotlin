package com.example.retrofitkotlin.presentation.ui.fragments.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.repositories.EpisodeRepository

class EpisodeViewModel(

    private val repository: EpisodeRepository
) : BaseViewModel() {

    var page = 1
    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)

}