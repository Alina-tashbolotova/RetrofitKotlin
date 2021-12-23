package com.example.retrofitkotlin.presentation.ui.fragments.location.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentLocationDetailBinding
import com.example.retrofitkotlin.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment() :
    BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    override val viewModel: LocationDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupRequest() = with(binding) {
        viewModel.locationDetailState.subscribe {
            progressBarLocationDetail.isVisible = it is UIState.Loading
            groupLocationDetail.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {

                }
                is UIState.Loading -> {

                }
                is UIState.Success -> {
                    txtItemIdLocation.text = it.data.id.toString()
                    txtItemName.text = it.data.name
                    txtItemType.text = it.data.type
                    txtItemDimension.text = it.data.dimension
                    txtItemCreated.text = it.data.created

                }
            }
        }
    }


}