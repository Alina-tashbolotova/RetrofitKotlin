package com.example.retrofitkotlin.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.retrofitkotlin.common.resource.Resource
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel (){

    protected fun <T> MutableStateFlow<UIState<T>>.subscribeTo(
        request: () -> Flow<Resource<T>>
    ){

        viewModelScope.launch(Dispatchers.IO) {
            request().collect {
                when(it){
                    is Resource.Loading -> {
                        this@subscribeTo.value = UIState.Loading()
                    }
                    is Resource.Error -> it.message?.let { error->
                        this@subscribeTo.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?.let { data ->
                        this@subscribeTo.value = UIState.Success(data)
                    }
                }
            }
        }

    }
}