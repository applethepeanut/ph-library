package com.atpfury.phlibrary.kotlin

interface BookRepoInterface {

    fun findByAuthor(author: String): List<Book>
}

data class BookRepo(val books: List<Book> = emptyList()) : BookRepoInterface {

    override fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }
}