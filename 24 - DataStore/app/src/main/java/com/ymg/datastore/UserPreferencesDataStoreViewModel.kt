package com.ymg.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferencesDataStoreViewModel @Inject constructor(
    private val repository: UserPreferencesDataStoreRepository
) : ViewModel() {
    fun run() = viewModelScope.launch {
        println("AccessToken: ${repository.accessToken.first()}")
        println("RefreshToken: ${repository.refreshToken.first()}")
        repository.setAccessToken("ACCESS")
        repository.setRefreshToken("REFRESH")
        println("AccessToken: ${repository.accessToken.first()}")
        println("RefreshToken: ${repository.refreshToken.first()}")
    }
}