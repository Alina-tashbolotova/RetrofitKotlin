package com.example.retrofitkotlin.presentation.ui.fragments.episode

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentEpisodeBinding
import com.example.retrofitkotlin.presentation.ui.adapters.EpisodeAdapter
import com.example.retrofitkotlin.presentation.ui.adapters.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModel()
    private val episodeAdapter = EpisodeAdapter(this::setOnItemListener)


    override fun initialization() = with(binding) {
        recyclerEpisode.layoutManager = LinearLayoutManager(context)
        recyclerEpisode.adapter = episodeAdapter.withLoadStateFooter(
            LoadStateAdapter { episodeAdapter.retry() })

        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBarEpisode.isVisible = loadStates.refresh is LoadState.Loading
        }
        swipeFresh()
    }

    private fun setOnItemListener(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(
                id = id
            )
        )
    }

    override fun setupObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }

    private fun swipeFresh() = with(binding) {
        episodeSwipeFresh.setOnRefreshListener {
            episodeAdapter.refresh()
            episodeSwipeFresh.isRefreshing = false
        }
    }


}