package com.example.retrofitkotlin.presentation.ui.fragments.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class CharacterViewModel(

    private val repository: CharacterRepository
) : BaseViewModel() {

    var page = 1
    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

}


