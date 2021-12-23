package com.example.retrofitkotlin.presentation.ui.fragments.character.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentCharacterDetailBinding
import com.example.retrofitkotlin.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    override val viewModel: CharacterDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)

    }

    override fun setupRequest() = with(binding) {
        viewModel.characterDetailState.subscribe {
            progressBarCharacterDetail.isVisible = it is UIState.Loading
            characterDetailGroup.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                    imageItemCharacterDetail.load(it.data.image)
                    txtItemIdDetail.text = it.data.id.toString()
                    txtItemNameDetail.text = it.data.name
                    txtItemStatusDetail.text = it.data.status
                    txtItemCharacterType.text = it.data.type
                    if (it.data.status == "Alive") {
                        viewStatusDetail.setBackgroundResource(R.drawable.oval)
                    } else if (it.data.status == "Dead") {
                        viewStatusDetail.setBackgroundResource(R.drawable.oval2)
                    }

                }
            }

        }

    }

}

