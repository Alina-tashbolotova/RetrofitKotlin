package com.example.retrofitkotlin.presentation.ui.fragments.character

import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(

    private val repository: CharacterRepository
) : BaseViewModel() {

    var page = 1
    fun fetchCharacters() = repository.fetchCharacters()

}


