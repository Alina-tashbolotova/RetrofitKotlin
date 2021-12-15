package com.example.retrofitkotlin.ui.fragments.character.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharacterDetailViewModel  @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel(){


    fun  fetchCharacter(id: Int): MutableLiveData<CharacterModel> {
        return repository.characterRepository(id)
    }
}