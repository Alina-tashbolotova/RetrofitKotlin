package com.example.retrofitkotlin.presentation.ui.fragments.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.repositories.LocationRepository

class LocationViewModel(

    private val repository: LocationRepository
) : BaseViewModel() {

    var page = 1
    fun fetchLocation() = repository.fetchLocations().cachedIn(viewModelScope)

}