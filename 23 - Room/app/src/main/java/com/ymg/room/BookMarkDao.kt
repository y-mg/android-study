package com.ymg.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO 는 데이터베이스 작업을 정의하는 인터페이스
 * OnConflictStrategy 는 Primary Key 충돌 시 정책을 설정
 */
@Dao
interface BookMarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY created ASC")
    suspend fun findAll(): List<BookMarkEntity> // 모든 북마크 조회

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookMarkEntity: BookMarkEntity) // 북마크 삽입

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun delete(id: Int) // 특정 북마크 삭제
}