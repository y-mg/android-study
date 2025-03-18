package com.ymg.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> get() = _liveData

    fun exampleSetValue() {
        _liveData.value = "1"
        println("Value: ${_liveData.value}") // 1
        _liveData.value = "2"
        println("Value: ${_liveData.value}") // 2
    }

    fun examplePostValue() {
        _liveData.postValue("1")
        println("Value: ${_liveData.value}") // null(메인 스레드 반영 전)
        _liveData.postValue("2")
        println("Value: ${_liveData.value}") // null(메인 스레드 반영 전)
    }
}