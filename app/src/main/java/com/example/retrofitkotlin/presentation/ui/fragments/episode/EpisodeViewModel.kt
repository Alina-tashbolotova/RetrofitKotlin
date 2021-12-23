package com.example.retrofitkotlin.presentation.ui.fragments.episode

import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(

    private val repository: EpisodeRepository
) : BaseViewModel() {

    var page = 1
    fun fetchEpisodes() = repository.fetchEpisodes()

}