package com.atpfury.phlibrary.kotlin

interface BookRepoInterface {

    fun findByAuthor(author: String): List<Book>
}

object BookRepo : BookRepoInterface {

    private val books = mutableListOf<Book>()

    override fun findByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }
}