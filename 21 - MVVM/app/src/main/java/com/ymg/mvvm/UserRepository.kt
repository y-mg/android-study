package com.ymg.mvvm

class UserRepository {
    private val userList = listOf(
        User(1, "홍길동", 25),
        User(2, "이순신", 30),
        User(3, "강감찬", 35)
    )

    fun getUsers(): List<User> {
        return userList
    }
}