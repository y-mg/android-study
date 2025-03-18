package com.ymg.paging

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {
    @Query("SELECT * FROM data ORDER BY id ASC")
    fun getAll(): PagingSource<Int, DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<DataEntity>)

    @Query("DELETE FROM data")
    suspend fun clearAll()
}