package com.ymg.lifecyclescope

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            // ViewModel 내에서 실행할 코루틴 작업
        }
    }
}