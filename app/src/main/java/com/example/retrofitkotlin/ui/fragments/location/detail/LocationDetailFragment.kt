package com.example.retrofitkotlin.ui.fragments.location.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitkotlin.databinding.FragmentLocationDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment(binding: FragmentLocationDetailBinding) : Fragment() {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocationDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toGetData()
    }

    private fun toGetData() = with(binding) {
        viewModel.fetchLocations(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                txtItemIdLocation.text = it.id.toString()
                txtItemName.text = it.name
                txtItemType.text = it.type
                txtItemDimension.text = it.dimension
                txtItemCreated.text = it.created

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}