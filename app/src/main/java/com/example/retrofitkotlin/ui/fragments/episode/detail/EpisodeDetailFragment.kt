package com.example.retrofitkotlin.ui.fragments.episode.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    ): View? {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toGetData()
    }

    private fun toGetData() = with(binding) {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                txtItemId.text = it.id.toString()
                txtItemName.text = it.name
                txtItemAirDate.text = it.air_date
                txtItemCreated.text = it.created
                txtItemEpisode.text = it.episode

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}