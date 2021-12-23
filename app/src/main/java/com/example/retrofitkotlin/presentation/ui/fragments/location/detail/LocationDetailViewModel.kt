package com.example.retrofitkotlin.presentation.ui.fragments.location.detail

import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.data.repositories.LocationRepository
import com.example.retrofitkotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(

    private val repository: LocationRepository
) : BaseViewModel() {

    private val _locationDetailState = MutableStateFlow<UIState<LocationModel>>(UIState.Loading())
    val locationDetailState: StateFlow<UIState<LocationModel>> = _locationDetailState

    fun fetchLocation(id: Int) {
        _locationDetailState.subscribeTo {
            repository.fetchLocation(id)
        }
    }
}