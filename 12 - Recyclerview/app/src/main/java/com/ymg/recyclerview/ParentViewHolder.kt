package com.ymg.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.ymg.recyclerview.databinding.ItemParentBinding

class ParentViewHolder(
    private val binding: ItemParentBinding,
    private val pool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parentItem: ParentItem) {
        binding.category.text = parentItem.category // 상위 제목

        // 가로 Child RecyclerView 설정
        binding.childRecyclerView.apply {
            adapter = ChildAdapter(parentItem.children)
            setRecycledViewPool(pool) // 부모 RecyclerView 에서 공유하는 ViewPool 설정
        }
    }
}