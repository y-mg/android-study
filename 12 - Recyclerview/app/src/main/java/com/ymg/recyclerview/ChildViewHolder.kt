package com.ymg.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.ymg.recyclerview.databinding.ItemChildBinding

class ChildViewHolder(
    private val binding: ItemChildBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChildItem) {
        binding.text.text = item.title
    }
}