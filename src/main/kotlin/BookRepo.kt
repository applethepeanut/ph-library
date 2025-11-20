package com.atpfury.phlibrary.kotlin


interface BookRepoInterface {

    fun add(book: Book): Result<String>
    fun findByAuthor(author: String): List<Book>
    fun findByTitle(title: String): List<Book>
    fun findByISBN(isbn: String): Book?
}

data class BookRepo(val books: MutableList<Book> = mutableListOf()) : BookRepoInterface {

    override fun add(book: Book): Result<String> {
        if (books.any { it.isbn == book.isbn }) {
            return Failure("The book already exists. ISBN:${book.isbn}")
        } else {
            books.add(book)
            return Success
        }
    }

    override fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author.lowercase().contains(author.lowercase()) }
    }

    override fun findByTitle(title: String): List<Book> {
        return books.filter { it.title.lowercase().contains(title.lowercase()) }
    }

    override fun findByISBN(isbn: String): Book? {
        return books.find { it.isbn == isbn }
    }
}