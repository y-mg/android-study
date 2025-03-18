package com.ymg.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val database = AppDatabase.getInstance(application.applicationContext)
    private val repository = BookMarkRepository(database.bookMarkDao())

    init {
        viewModelScope.launch {
            addBookmark(
                BookMarkEntity(
                    title = "Title",
                    description = "Description"
                )
            )
            getBooks()
            removeBookmark(1)
            getBooks()
        }
    }

    private fun getBooks() = viewModelScope.launch {
        println(repository.findALl())
    }

    private fun addBookmark(
        bookmark: BookMarkEntity
    ) = viewModelScope.launch {
        repository.insert(bookmark)
    }

    private fun removeBookmark(
        id: Int
    ) = viewModelScope.launch {
        repository.delete(id)
    }
}