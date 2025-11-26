package com.atpfury.phlibrary.kotlin

import java.util.*


interface BookRepoInterface {

    fun add(book: Book): Result<String, Unit>
    fun findByAuthor(author: String): List<Book>
    fun findByTitle(title: String): List<Book>
    fun getByISBN(isbn: String): Book?
    fun get(id: UUID): Result<String, Book>
}

data class BookRepo(val books: MutableList<Book> = mutableListOf()) : BookRepoInterface {

    override fun add(book: Book): Result<String, Unit> {
        if (books.any { it.id == book.id }) {
            return Failure("The book ${book.id} already exists.")
        } else {
            books.add(book)
            return Success(Unit)
        }
    }

    private fun findBy(propertySelector: (Book) -> String, term: String): List<Book> =
        books.filter { (propertySelector(it)).lowercase().contains(term.lowercase()) }

    override fun findByAuthor(author: String): List<Book> {
        return findBy({ it.author }, author)
    }

    override fun findByTitle(title: String): List<Book> {
        return findBy({ it.title }, title)
    }

    override fun getByISBN(isbn: String): Book? {
        return books.find { it.isbn == isbn }
    }

    override fun get(id: UUID): Result<String, Book> {
        val book = books.find { it.id == id }
        return if (book != null) Success(book)
        else Failure("No book found with id: $id")
    }
}