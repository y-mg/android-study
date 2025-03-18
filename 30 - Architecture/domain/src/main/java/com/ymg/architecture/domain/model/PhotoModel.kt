package com.ymg.architecture.domain.model

import java.io.Serializable

data class PhotoModel(
    val id: String,
    val thumb: String
): Serializable {
    constructor() : this(
        id = "",
        thumb = ""
    )
}