package com.ymg.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ymg.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = ItemAdapter(listOf(
                Item("Title 1", "Description 1"),
                Item("Title 2", "Description 2"),
                Item("Title 3", "Description 3")
            ))
        }
    }

    private fun setupRecyclerViewSetHasStableIds() {
        binding.recyclerView.apply {
            adapter = ItemAdapter(listOf(
                Item("Title 1", "Description 1"),
                Item("Title 2", "Description 2"),
                Item("Title 3", "Description 3")
            )).apply {
                setHasStableIds(true) // ID 가 고정되었음을 명시
            }
        }
    }

    private fun setupRecyclerViewSetItemViewCacheSize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemAdapter(listOf(
                Item("Title 1", "Description 1"),
                Item("Title 2", "Description 2"),
                Item("Title 3", "Description 3")
            ))
            setItemViewCacheSize(10) // 10개의 뷰를 캐시에 유지
        }
    }

    private fun setupRecyclerViewSetHasFixedSize() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemAdapter(listOf(
                Item("Title 1", "Description 1"),
                Item("Title 2", "Description 2"),
                Item("Title 3", "Description 3")
            ))
            setHasFixedSize(true) // 크기가 고정된 경우 성능 최적화
        }
    }

    private fun setupParentRecyclerView() {
        val parentList = listOf(
            ParentItem("Category 1", listOf(ChildItem("Item A"), ChildItem("Item B"), ChildItem("Item C"))),
            ParentItem("Category 2", listOf(ChildItem("Item D"), ChildItem("Item E"), ChildItem("Item F"))),
            ParentItem("Category 3", listOf(ChildItem("Item G"), ChildItem("Item H"), ChildItem("Item I")))
        )

        binding.recyclerView.apply {
            adapter = ParentAdapter(parentList)
        }
    }
}