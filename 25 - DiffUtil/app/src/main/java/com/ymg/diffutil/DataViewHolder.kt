package com.ymg.diffutil

import androidx.recyclerview.widget.RecyclerView
import com.ymg.diffutil.databinding.ItemDataBinding

class DataViewHolder(
    private val binding: ItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Data) {
        binding.title.text = data.title
        binding.description.text = data.description
    }
}