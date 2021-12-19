package com.example.retrofitkotlin.ui.fragments.location.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.data.repositories.EpisodeRepository
import com.example.retrofitkotlin.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel  @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    fun  fetchLocation(id: Int) = repository.fetchLocation(id)
}