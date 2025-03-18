package com.ymg.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ymg.fragment.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var _binding: FragmentMainBinding
    private val binding get() = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MainFragment", "onAttach() 호출")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainFragment", "onCreate() 호출")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainFragment", "onCreateView() 호출")
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MainFragment", "onViewCreated() 호출")

        binding.text.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SubFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("MainFragment", "onViewStateRestored() 호출")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainFragment", "onStart() 호출")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainFragment", "onResume() 호출")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainFragment", "onPause() 호출")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainFragment", "onStop() 호출")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainFragment", "onSaveInstanceState() 호출")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MainFragment", "onDestroyView() 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainFragment", "onDestroy() 호출")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MainFragment", "onDetach() 호출")
    }
}