package com.example.retrofitkotlin.ui.fragments.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitkotlin.common.resource.Resource
import com.example.retrofitkotlin.databinding.FragmentEpisodeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private var _binding: FragmentEpisodeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRequest()
    }

    private fun setupRequest() = with(binding) {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                progressBarEpisodeDetail.isVisible = it is Resource.Loading
                groupEpisodeDetail.isVisible = it !is Resource.Loading
                when (it) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            txtItemId.text = data.id.toString()
                            txtItemName.text = data.name
                            txtItemAirDate.text = data.air_date
                            txtItemCreated.text = data.created
                            txtItemEpisode.text = data.episode

                        }
                    }
                }
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}