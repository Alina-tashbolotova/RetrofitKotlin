package com.example.retrofitkotlin.presentation.ui.fragments.episode.detail

import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.repositories.EpisodeRepository
import com.example.retrofitkotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(

    private val repository: EpisodeRepository
) : BaseViewModel() {

    private val _episodeDetailState = MutableStateFlow<UIState<EpisodeModel>>(UIState.Loading())
    val episodeDetailState: StateFlow<UIState<EpisodeModel>> = _episodeDetailState

    fun fetchEpisode(id: Int) {
        _episodeDetailState.subscribeTo {
            repository.fetchEpisode(id)
        }
    }

}