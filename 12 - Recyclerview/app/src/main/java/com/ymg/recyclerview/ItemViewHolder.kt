package com.ymg.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.ymg.recyclerview.databinding.ItemViewBinding

class ItemViewHolder(
    private val binding: ItemViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.title.text = item.title
        binding.description.text = item.description
    }
}