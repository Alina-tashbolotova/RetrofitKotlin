package com.example.retrofitkotlin.servicelocator

import com.example.retrofitkotlin.data.network.RetrofitClient
import com.example.retrofitkotlin.data.repositories.CharacterRepository
import com.example.retrofitkotlin.data.repositories.EpisodeRepository
import com.example.retrofitkotlin.data.repositories.LocationRepository
import com.example.retrofitkotlin.presentation.ui.fragments.character.CharacterViewModel
import com.example.retrofitkotlin.presentation.ui.fragments.character.detail.CharacterDetailViewModel
import com.example.retrofitkotlin.presentation.ui.fragments.episode.EpisodeViewModel
import com.example.retrofitkotlin.presentation.ui.fragments.episode.detail.EpisodeDetailViewModel
import com.example.retrofitkotlin.presentation.ui.fragments.location.LocationViewModel
import com.example.retrofitkotlin.presentation.ui.fragments.location.detail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodeApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}
val repositoriesModule = module {
    factory { CharacterRepository(get()) }
    factory { EpisodeRepository(get()) }
    factory { LocationRepository(get()) }
}
val viewModelsModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }

}