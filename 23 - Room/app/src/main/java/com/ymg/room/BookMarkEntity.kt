package com.ymg.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

/**
 * Entity 는 데이터베이스 테이블을 정의하는 클래스
 * @PrimaryKey(autoGenerate = true) 로 Primary Key 자동 생성 가능
 */
@Entity(tableName = "bookmarks")
@TypeConverters(DateConverter::class) // 필요시 TypeConverter 사용
data class BookMarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "created")
    val created: Date = Date() // 날짜 타입 저장 예시
)