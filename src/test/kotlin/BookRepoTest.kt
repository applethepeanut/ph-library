package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class BookRepoTest {

    @Test
    fun `findByAuthor is case insensitive`() {
        val books = listOf(Book("Bobby Uncle", "awesome book", "1234"))
        val repo = BookRepo(books)
        val result = repo.findByAuthor("BoBbY UNclE")
        assertEquals(books, result)
    }

    @Test
    fun `findByAuthor returns no results when no matches are found`() {
        val repo = BookRepo()
        val result = repo.findByAuthor("title")
        assertEquals(0, result.size)
    }
}