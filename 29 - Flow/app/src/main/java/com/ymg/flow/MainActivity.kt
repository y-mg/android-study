package com.ymg.flow

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ymg.flow.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val flowViewModel by viewModels<FlowViewModel>()
    private val stateFlowViewModel by viewModels<StateFlowViewModel>()
    private val sharedFlowViewModel by viewModels<SharedFlowViewModel>()

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
        flowObserver()
        stateFlowObserver()
        sharedFlowObserver()
        exampleCallbackFlow()
    }

    private fun flowObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flowViewModel.flow.collect { value ->
                    println("Received Flow: $value")
                }
            }
        }
    }

    private fun stateFlowObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlowViewModel.stateFlow.collect { value ->
                    println("Received StateFlow: $value")
                }
            }
        }
    }

    private fun sharedFlowObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedFlowViewModel.sharedFlow.collect { value ->
                    println("Received SharedFlow: $value")
                }
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun exampleCallbackFlow() {
        binding.editText.textChanges()
            .debounce(300) // 300ms 동안 입력이 없을 때만 데이터 방출
            .distinctUntilChanged() // 중복 값 방출 방지
            .mapLatest { text ->
                Log.d("EditTextFlow", "입력된 텍스트: $text")
            }
            .launchIn(lifecycleScope)
    }
}