package com.ymg.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ymg.fragment.databinding.FragmentSubBinding

class SubFragment : Fragment() {
    private lateinit var _binding: FragmentSubBinding
    private val binding get() = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("SubFragment", "onAttach() 호출")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SubFragment", "onCreate() 호출")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SubFragment", "onCreateView() 호출")
        _binding = FragmentSubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SubFragment", "onViewCreated() 호출")

        binding.text.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("SubFragment", "onViewStateRestored() 호출")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SubFragment", "onStart() 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SubFragment", "onResume() 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SubFragment", "onPause() 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SubFragment", "onStop() 호출")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("SubFragment", "onSaveInstanceState() 호출")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SubFragment", "onDestroyView() 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SubFragment", "onDestroy() 호출")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("SubFragment", "onDetach() 호출")
    }
}