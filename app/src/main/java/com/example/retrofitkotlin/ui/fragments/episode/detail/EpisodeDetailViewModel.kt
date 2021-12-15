package com.example.retrofitkotlin.ui.fragments.episode.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class EpisodeDetailViewModel  @Inject constructor(
    private val repository: EpisodeRepository
) : ViewModel() {


    fun  fetchEpisode(id: Int): MutableLiveData<EpisodeModel> {
        return repository.fetchEpisode(id)
    }
}