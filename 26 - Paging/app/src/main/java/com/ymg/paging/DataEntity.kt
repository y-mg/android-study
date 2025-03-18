package com.ymg.paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey val id: Int = 0,
    val name: String
)