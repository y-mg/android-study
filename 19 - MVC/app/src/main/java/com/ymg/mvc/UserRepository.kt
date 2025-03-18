package com.ymg.mvc

class UserRepository {
    // 가상의 데이터베이스에서 사용자 데이터를 가져오는 메소드
    fun getUser(id: Int): User {
        // 실제 애플리케이션에서는 네트워크나 DB에서 데이터를 가져옵니다.
        return User(id, "John Doe")  // 예시 데이터
    }
}