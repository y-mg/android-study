package com.ymg.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter

// 커스텀 BindingAdapter 함수 정의
@BindingAdapter("addText")
fun TextView.addText(text: String) {
    this.text = "$text BindingAdapter"
}