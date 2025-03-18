package com.ymg.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 앱의 Room 데이터베이스 정의
 * exportSchema = false 로 설정하면 스키마 파일이 생성되지 않음
 */
@Database(
    entities = [BookMarkEntity::class], // 사용할 엔티티 클래스
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao // DAO 정의

    companion object {
        private const val DB_NAME = "app.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * 싱글톤 인스턴스 제공
         * synchronized 블록을 통해 다중 스레드 환경에서 데이터베이스의 안정성 보장
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration() // 스키마 변경 시 데이터 초기화
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}