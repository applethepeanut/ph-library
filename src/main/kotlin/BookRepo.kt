package com.atpfury.phlibrary.kotlin

interface BookRepoInterface {

    fun findByAuthor(author: String): List<Book>
    fun findByTitle(title: String): List<Book>
    fun findByISBN(isbn: String): Book?
}

data class BookRepo(val books: List<Book> = emptyList()) : BookRepoInterface {

    override fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author.lowercase().contains(author.lowercase()) }
    }

    override fun findByTitle(title: String): List<Book> {
        return books.filter { it.title.lowercase() .contains(title.lowercase()) }
    }

    override fun findByISBN(isbn: String): Book? {
        return books.find { it.isbn == isbn }
    }
}