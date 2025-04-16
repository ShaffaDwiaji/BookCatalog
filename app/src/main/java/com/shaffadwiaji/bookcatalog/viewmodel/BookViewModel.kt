package com.shaffadwiaji.bookcatalog.viewmodel

import androidx.lifecycle.*
import com.shaffadwiaji.bookcatalog.data.Book
import com.shaffadwiaji.bookcatalog.data.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel()  {

    val allBooks: LiveData<List<Book>> = repository.allBooks

    fun insertBook(book: Book) = viewModelScope.launch {
        repository.insertBook(book)
    }

    fun update(book: Book) = viewModelScope.launch {
        repository.updateBook(book)
    }

    fun delete(book: Book) = viewModelScope.launch {
        repository.deleteBook(book)
    }
}