package com.atpfury.phlibrary.kotlin

interface BookRepoInterface {

    fun findByAuthor(author: String): List<Book>
    fun findByTitle(title: String): List<Book>
}

data class BookRepo(val books: List<Book> = emptyList()) : BookRepoInterface {

    override fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author.lowercase() == author.lowercase() }
    }

    override fun findByTitle(title: String): List<Book> {
        return books.filter { it.title.lowercase() == title.lowercase() }
    }
}