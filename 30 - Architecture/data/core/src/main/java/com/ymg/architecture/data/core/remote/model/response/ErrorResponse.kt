package com.ymg.architecture.data.core.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("timestamp")
    val timestamp: String,

    // HTTP Code
    @SerialName("status")
    val status: Int,

    // API Code
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("path")
    val path: String
)
