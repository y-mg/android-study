package com.ymg.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData("Title")
    val text: LiveData<String> get() = _text

    fun onButtonClicked() {
        println("클릭")
    }
}