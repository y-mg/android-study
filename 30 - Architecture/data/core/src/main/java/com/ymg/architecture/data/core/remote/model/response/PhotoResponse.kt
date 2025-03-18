package com.ymg.architecture.data.core.remote.model.response

import com.ymg.architecture.domain.model.PhotoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    @SerialName("id")
    val id: String,

    @SerialName("urls")
    val urls: Urls,
) {
    companion object {
        fun PhotoResponse.toModel() = PhotoModel(
            id = id,
            thumb = urls.thumb
        )
    }

    @Serializable
    data class Urls(
        @SerialName("full")
        val full: String,

        @SerialName("raw")
        val raw: String,

        @SerialName("regular")
        val regular: String,

        @SerialName("small")
        val small: String,

        @SerialName("thumb")
        val thumb: String
    )
}