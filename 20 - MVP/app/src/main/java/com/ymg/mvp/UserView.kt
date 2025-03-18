package com.ymg.mvp

interface UserView {
    fun showUser(user: User)
    fun showError(message: String)
}