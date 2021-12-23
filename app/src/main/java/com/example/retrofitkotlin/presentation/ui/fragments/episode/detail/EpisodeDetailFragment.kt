package com.example.retrofitkotlin.presentation.ui.fragments.episode.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentEpisodeDetailBinding
import com.example.retrofitkotlin.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    override val viewModel: EpisodeDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequest() = with(binding) {
        viewModel.episodeDetailState.subscribe {
            progressBarEpisodeDetail.isVisible = it is UIState.Loading
            groupEpisodeDetail.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {

                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    txtItemId.text = it.data.id.toString()
                    txtItemName.text = it.data.name
                    txtItemAirDate.text = it.data.air_date
                    txtItemCreated.text = it.data.created
                    txtItemEpisode.text = it.data.episode

                }
            }

        }
    }

}