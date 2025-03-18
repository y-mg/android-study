package com.ymg.serialization

import java.io.Serializable

data class MySerializableObject(
    val data: Int,
    val name: String
) : Serializable {
    // 직렬화할 필드 정의
}