package com.ymg.mvc

import android.widget.TextView

class UserView(
    private val textView: TextView
) {
    // User 정보를 TextView 에 표시하는 메소드
    fun displayUserName(user: User) {
        textView.text = user.name  // TextView 에 사용자 이름을 설정
    }
}