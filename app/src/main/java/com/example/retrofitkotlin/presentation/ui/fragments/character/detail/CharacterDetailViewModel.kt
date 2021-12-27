package com.example.retrofitkotlin.presentation.ui.fragments.character.detail

import com.example.retrofitkotlin.common.base.BaseViewModel
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.data.repositories.CharacterRepository
import com.example.retrofitkotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel(

    private val repository: CharacterRepository
) : BaseViewModel() {

    private val _characterDetailState = MutableStateFlow<UIState<CharacterModel>>(UIState.Loading())
    val characterDetailState: StateFlow<UIState<CharacterModel>> = _characterDetailState

    fun fetchCharacter(id: Int) {
        _characterDetailState.subscribeTo {
            repository.fetchCharacter(id)
        }
    }
}