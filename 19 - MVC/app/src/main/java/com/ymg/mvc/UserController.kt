package com.ymg.mvc

class UserController(
    private val view: UserView, // View 는 화면 표시를 담당
    private val repository: UserRepository // Repository 는 데이터를 제공
) {
    // 사용자를 로드하여 화면에 표시하는 메소드
    fun loadUser(id: Int) {
        val user = repository.getUser(id) // Repository 에서 사용자 정보 가져오기
        view.displayUserName(user) // View 에 사용자 이름 표시
    }
}