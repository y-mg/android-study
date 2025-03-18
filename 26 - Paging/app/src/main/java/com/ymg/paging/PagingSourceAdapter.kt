package com.ymg.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ymg.paging.databinding.ItemDataBinding

class PagingSourceAdapter: PagingDataAdapter<Data, ItemDataViewHolder>(
    object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataViewHolder =
        ItemDataViewHolder(
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ItemDataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
