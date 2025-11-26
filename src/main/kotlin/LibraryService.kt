package com.atpfury.phlibrary.kotlin

import java.util.UUID


interface LibraryServiceInterface {
    fun findBooksByAuthor(author: String): List<Book>
    fun findBooksByTitle(title: String): List<Book>
    fun getBook(id: UUID): Result<String, Book>
    fun getBookByISBN(isbn: String): Book?
    fun addBooks(vararg books: Book): Result<List<String>, Unit>
    fun borrowBook(book: Book): Result<String, Book>
}

class LibraryService(private val bookRepo: BookRepo = BookRepo()) : LibraryServiceInterface {

    override fun borrowBook(book: Book): Result<String, Book> {
        return bookRepo.books.find { it.id == book.id }
            ?.let { book ->
                if(book.status == BookStatus.AVAILABLE) {
                    //TODO bookRepo.upddate(book.copy(status = BookStatus.BORROWED))
                    Success(book)
                }
                else Failure("Book ${book.id} is not available.")
            }
            ?: Failure("Book ${book.id} not found.")
    }

    override fun getBook(id: UUID): Result<String, Book> {
        TODO("Not yet implemented")
    }

    override fun addBooks(vararg books: Book): Result<List<String>, Unit> {
        val ops = books.map { bookRepo.add(it) }
        val failures = ops.filterIsInstance<Failure<String>>().map { it.value }

        return if (failures.isEmpty()) Success(Unit) else Failure(failures)
    }

    override fun findBooksByAuthor(author: String): List<Book> {
        return bookRepo.findByAuthor(author)
    }

    override fun findBooksByTitle(title: String): List<Book> {
        return bookRepo.findByTitle(title)
    }

    override fun getBookByISBN(isbn: String): Book? {
        return bookRepo.getByISBN(isbn)
    }

}