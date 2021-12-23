package com.example.retrofitkotlin.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.retrofitkotlin.common.base.BaseComparator
import com.example.retrofitkotlin.data.network.dtos.CharacterModel
import com.example.retrofitkotlin.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClick: (name: String, id: Int) -> Unit,
    private val onLongClick: (image: String) -> Unit
) : PagingDataAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(
    BaseComparator()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.name, it.id)
                }
            }
            binding.root.setOnLongClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onLongClick(it.image)
                }
                return@setOnLongClickListener false
            }

        }

        fun onBind(model: CharacterModel) = with(binding) {

            txtItemCharacterName.text = model.name
            imageItemCharacter.load(model.image)

        }


    }


}