package com.ymg.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProtoDataStoreViewModel @Inject constructor(
    private val repository: UserProtoDataStoreRepository
) : ViewModel() {
    fun run() = viewModelScope.launch {
        println("이름: ${repository.userName.first()}")
        println("이메일: ${repository.userEmail.first()}")
        repository.setUserName("홍길동")
        repository.setEmail("gdh@google.com")
        println("이름: ${repository.userName.first()}")
        println("이메일: ${repository.userEmail.first()}")
    }
}