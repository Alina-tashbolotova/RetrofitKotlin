package com.example.retrofitkotlin.presentation.ui.fragments.location

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentLocationBinding
import com.example.retrofitkotlin.presentation.ui.adapters.LocationAdapter
import com.example.retrofitkotlin.presentation.ui.adapters.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModel()

    private val locationAdapter =
        LocationAdapter(this::setOnItemClick)


    override fun initialization() = with(binding) {
        recyclerLocation.layoutManager = LinearLayoutManager(context)
        recyclerLocation.adapter = locationAdapter.withLoadStateFooter(
            LoadStateAdapter { locationAdapter.retry() })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerLocation.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBarLocation.isVisible = loadStates.refresh is LoadState.Loading
        }
        swipeFresh()
    }

    private fun setOnItemClick(id: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(
                id = id
            )
        )
    }


    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchLocation().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }

    private fun swipeFresh() = with(binding) {
        locationSwipeFresh.setOnRefreshListener {
            locationAdapter.refresh()
            locationSwipeFresh.isRefreshing = false
        }
    }


}