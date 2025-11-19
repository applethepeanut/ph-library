package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class BookRepoTest {

    val book1 = Book("Bobby Uncle", "Awesome Book", "1234")
    val book2 = Book("Uncle Bob", "Book 1", "1234-123")
    val book3 = Book("Uncle Bob", "Book 2", "1234-123")

    val books = listOf(
        book1, book2, book3, Book("me", "I Need Imagination", "5672"),
    )

    @Test
    fun `findByAuthor should match all results`() {
        val repo = BookRepo(books)
        val result = repo.findByAuthor("uncle bob")
        assertEquals(setOf(book3, book2), result.toSet())
    }

    @Test
    fun `findByAuthor is case insensitive`() {
        val repo = BookRepo(books)
        val result = repo.findByAuthor("BoBbY UNclE")
        assertEquals(listOf(book1), result)
    }

    @Test
    fun `findByAuthor returns no results when no matches are found`() {
        val repo = BookRepo(books)
        val result = repo.findByAuthor("dude")
        assertEquals(0, result.size)
    }
}