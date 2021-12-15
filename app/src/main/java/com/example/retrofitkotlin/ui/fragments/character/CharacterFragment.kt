package com.example.retrofitkotlin.ui.fragments.character

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.common.base.BaseFragment
import com.example.retrofitkotlin.databinding.FragmentCharacterBinding
import com.example.retrofitkotlin.ui.adapters.CharacterAdapter
import com.example.retrofitkotlin.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()

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
    }

    override fun setupObservers() {

        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
    }

    override fun swipeFresh() {
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

    override fun setupListeners() {
    }

    override fun setupRequest() {
    }


}