package com.example.retrofitkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.common.base.BaseComparator
import com.example.retrofitkotlin.data.network.dtos.LocationModel
import com.example.retrofitkotlin.databinding.ItemLocationBinding
import kotlin.reflect.KFunction1

class LocationAdapter(

    private val onItemClick: KFunction1<Int, Unit>
) : PagingDataAdapter<LocationModel, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(model: LocationModel) = with(binding) {
            txtItemIdLocation.text = model.id.toString()
            txtItemNameLocation.text = model.name
        }

    }
}