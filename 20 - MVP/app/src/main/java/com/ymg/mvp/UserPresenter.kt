package com.ymg.mvp

class UserPresenter(
    private val view: UserView,
    private val repository: UserRepository
) {
    fun loadUser() {
        try {
            val user = repository.getUser()
            view.showUser(user)
        } catch (e: Exception) {
            view.showError("사용자 정보를 불러오는 데 실패했습니다.")
        }
    }
}