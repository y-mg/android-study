package com.ymg.paging

import androidx.recyclerview.widget.RecyclerView
import com.ymg.paging.databinding.ItemDataBinding

class ItemDataViewHolder(
    private val binding: ItemDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Data) {
        binding.name.text = data.name
    }
    fun bind(dataEntity: DataEntity) {
        binding.name.text = dataEntity.name
    }
}