package com.ymg.room

class BookMarkRepository(
    private val dao: BookMarkDao
) {
    suspend fun findALl(): List<BookMarkEntity> {
        return dao.findAll()
    }

    suspend fun insert(bookMarkEntity: BookMarkEntity) {
        dao.insert(bookMarkEntity)
    }

    suspend fun delete(id: Int) {
        dao.delete(id)
    }
}