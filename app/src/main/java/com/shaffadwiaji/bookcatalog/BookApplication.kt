package com.shaffadwiaji.bookcatalog

import android.app.Application
import com.shaffadwiaji.bookcatalog.data.BookDatabase
import com.shaffadwiaji.bookcatalog.data.BookRepository

class BookApplication : Application() {
    val database by lazy { BookDatabase.getDatabase(this) }
    val repository by lazy { BookRepository(database.bookDao()) }
}