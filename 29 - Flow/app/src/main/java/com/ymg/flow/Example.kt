package com.ymg.flow

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun EditText.textChanges(): Flow<CharSequence?> = callbackFlow {
    // TextWatcher 리스너를 정의하여 EditText의 텍스트 변경 이벤트를 감지
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun afterTextChanged(s: Editable?) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            trySend(s).isSuccess // 실패 여부를 처리할 필요가 없는 경우 무시 가능
        }
    }

    // EditText 에 TextWatcher 추가
    addTextChangedListener(textWatcher)

    // 초기값 방출 (현재 EditText 에 입력된 값 반영)
    awaitClose { removeTextChangedListener(textWatcher) }
}.onStart { emit(text) }