package com.example.retrofitkotlin.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.resource.Resource
import com.example.retrofitkotlin.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRequests()

    }

    private fun setupRequests() = with(binding) {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                progressBarCharacterDetail.isVisible = it is Resource.Loading
                characterDetailGroup.isVisible = it !is Resource.Loading
                when (it) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            imageItemCharacterDetail.load(data.image)
                            txtItemIdDetail.text = data.id.toString()
                            txtItemNameDetail.text = data.name
                            txtItemStatusDetail.text = data.status
                            txtItemCharacterType.text = data.type
                            if (data.status == "Alive") {
                                viewStatusDetail.setBackgroundResource(R.drawable.oval)
                            } else if (data.status == "Dead") {
                                viewStatusDetail.setBackgroundResource(R.drawable.oval2)
                            }

                        }
                    }
                }
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

