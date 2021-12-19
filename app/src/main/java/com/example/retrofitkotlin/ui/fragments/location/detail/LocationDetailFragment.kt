package com.example.retrofitkotlin.ui.fragments.location.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitkotlin.common.resource.Resource
import com.example.retrofitkotlin.databinding.FragmentLocationDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment() : Fragment() {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRequest()
    }

    private fun setupRequest() = with(binding) {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                progressBarLocationDetail.isVisible = it is Resource.Loading
                groupLocationDetail.isVisible = it !is Resource.Loading

                when (it) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            txtItemIdLocation.text = data.id.toString()
                            txtItemName.text = data.name
                            txtItemType.text = data.type
                            txtItemDimension.text = data.dimension
                            txtItemCreated.text = data.created
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