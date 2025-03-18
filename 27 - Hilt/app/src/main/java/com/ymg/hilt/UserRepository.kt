package com.ymg.hilt

import javax.inject.Inject

interface UserRepository {
    fun getName(): String
}

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getName(): String = "홍길동"
}