package com.ymg.architecture.domain.remote.api.exception

data class ApiException(
    override val message: String,
    val status: Int,
    val code: Int
) : RuntimeException()
