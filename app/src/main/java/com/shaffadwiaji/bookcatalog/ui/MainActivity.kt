package com.shaffadwiaji.bookcatalog.ui

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shaffadwiaji.bookcatalog.BookApplication
import com.shaffadwiaji.bookcatalog.R
import com.shaffadwiaji.bookcatalog.adapter.BookAdapter
import com.shaffadwiaji.bookcatalog.data.Book
import com.shaffadwiaji.bookcatalog.viewmodel.BookViewModel
import com.shaffadwiaji.bookcatalog.viewmodel.BookViewModelFactory
import androidx.appcompat.app.AlertDialog

class MainActivity : ComponentActivity() {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var recyclerView: RecyclerView

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as BookApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvBooks)
        bookAdapter = BookAdapter(emptyList())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookAdapter
        }

        val fab: FloatingActionButton = findViewById(R.id.fabAddBook)
        fab.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_add_book, null)
            val etTitle = dialogView.findViewById<EditText>(R.id.etTitle)
            val etAuthor = dialogView.findViewById<EditText>(R.id.etAuthor)

            AlertDialog.Builder(this)
                .setTitle("Tambah Buku")
                .setView(dialogView)
                .setPositiveButton("Simpan") { _, _ ->
                    val title = etTitle.text.toString()
                    val author = etAuthor.text.toString()
                    if (title.isNotEmpty() && author.isNotEmpty()) {
                        val newBook = Book(title = title, author = author)
                        bookViewModel.insertBook(newBook)
                    }
                }
                .setNegativeButton("Batal", null)
                .show()
        }


        bookViewModel.allBooks.observe(this) { books ->
            bookAdapter.setBooks(books)
        }
    }
}