package com.example.retrofitkotlin.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.common.base.BaseComparator
import com.example.retrofitkotlin.data.network.dtos.EpisodeModel
import com.example.retrofitkotlin.databinding.ItemEpisodeBinding

class EpisodeAdapter(

    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<EpisodeModel, EpisodeAdapter.EpisodeViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(model: EpisodeModel) = with(binding) {
            txtItemIdEpisode.text = model.id.toString()
            txtItemNameEpisode.text = model.name
        }

    }
}
