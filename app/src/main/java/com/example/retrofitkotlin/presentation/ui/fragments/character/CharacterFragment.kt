package com.example.retrofitkotlin.presentation.ui.fragments.character

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentCharacterBinding
import com.example.retrofitkotlin.presentation.ui.adapters.CharacterAdapter
import com.example.retrofitkotlin.presentation.ui.adapters.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModel()

    private val characterAdapter =
        CharacterAdapter(
            this::setOnItemClickListener,
            this::setOnItemLongClickListener
        )


    override fun initialization() = with(binding) {
        recyclerCharacter.layoutManager = LinearLayoutManager(context)
        recyclerCharacter.adapter = characterAdapter.withLoadStateFooter(
            LoadStateAdapter { characterAdapter.retry() })

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBarCharacter.isVisible = loadStates.refresh is LoadState.Loading
        }
        swipeFresh()
    }

    override fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)

            }

        }
    }

    private fun swipeFresh() {
        binding.characterSwipeFresh.setOnRefreshListener {
            characterAdapter.refresh()
            binding.characterSwipeFresh.isRefreshing = false
        }
    }

    private fun setOnItemClickListener(name: String, id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionNavigationCharacterToCharacterDetailFragment(
                label = "${"Character"} $name",
                id = id
            )
        )
    }

    private fun setOnItemLongClickListener(photo: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionNavigationCharacterToCharacterDialogFragment(
                image = photo
            )
        )
    }


}