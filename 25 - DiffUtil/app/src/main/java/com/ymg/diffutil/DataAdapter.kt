package com.ymg.diffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ymg.diffutil.databinding.ItemDataBinding

class DataAdapter : ListAdapter<Data, DataViewHolder>(
    object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        // ListAdapter 에서는 getItem(position) 을 사용하여 리스트에서 아이템을 가져옴
        // currentList[position] 와 동일하지만, getItem(position) 을 사용하는 것이 더 직관적이며 가독성이 좋음
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    // 외부에서 데이터를 갱신할 때 사용하는 메서드
    fun submitData(list: List<Data>) {
        submitList(list)
    }
}