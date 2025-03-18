package com.ymg.mvp

interface UserRepository {
    fun getUser(): User
}

class UserRepositoryImpl : UserRepository {
    override fun getUser(): User {
        return User(name = "홍길동", age = 30)
    }
}