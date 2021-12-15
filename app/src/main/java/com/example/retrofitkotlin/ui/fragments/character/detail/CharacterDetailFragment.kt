package com.example.retrofitkotlin.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment (){
    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toGetData()

    }

    private fun toGetData() = with(binding) {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner) {
                imageItemCharacterDetail.load(it.image)
                txtItemIdDetail.text = it.id.toString()
                txtItemNameDetail.text = it.name
                txtItemStatusDetail.text = it.status
                txtItemCharacterType.text = it.type

                if (it.status == "Alive") {
                    viewStatusDetail.setBackgroundResource(R.drawable.oval)
                } else if (it.status == "Dead") {
                    viewStatusDetail.setBackgroundResource(R.drawable.oval2)
                }

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

