package com.ymg.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ymg.recyclerview.databinding.ItemParentBinding

class ParentAdapter(
    private val items: List<ParentItem>
) : RecyclerView.Adapter<ParentViewHolder>() {
    // 모든 Child RecyclerView 가 공유할 ViewPool 생성
    private val sharedPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding, sharedPool)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}