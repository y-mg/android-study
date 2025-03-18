package com.ymg.paging

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ymg.paging.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private val pagingSourceAdapter by lazy { PagingSourceAdapter() }
    private val remoteMediatorAdapter by lazy { RemoteMediatorAdapter() }

    private val pagingSourceViewModel: PagingSourceViewModel by viewModels()
    private val remoteMediatorViewModel: RemoteMediatorViewModel by viewModels()

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
        binding.pagingSourceList.apply {
            adapter = pagingSourceAdapter
        }
        binding.remoteMediatorList.apply {
            adapter = remoteMediatorAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                pagingSourceViewModel.pagingDataFlow.collectLatest {
                    pagingSourceAdapter.submitData(it)
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                remoteMediatorViewModel.pagingDataFlow.collectLatest {
                    remoteMediatorAdapter.submitData(it)
                }
            }
        }
    }
}