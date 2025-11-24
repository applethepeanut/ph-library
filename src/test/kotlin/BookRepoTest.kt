package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class BookRepoTest {

    private fun randomStatus(): BookStatus =
        BookStatus.entries.random()

    val book1 = Book(UUID.randomUUID(), "Bobby Uncle", "Awesome Book", "1234-222", randomStatus())
    val book2 = Book(UUID.randomUUID(), "Uncle Bob", "Book 1", "1234-123", randomStatus())
    val book3 = Book(UUID.randomUUID(), "Uncle Bob", "Book 2", "1234-345", randomStatus())
    val book4 = Book(UUID.randomUUID(), "me", "Book 1", "1234-678", randomStatus())

    val books = mutableListOf(
        book1, book2, book3, book4
    )

    @Test
    fun `add fails to add a book with the same id`() {
        val repo = BookRepo(books)
        val result = repo.add(book1)
        assertEquals(Failure("The book ${book1.id} already exists."), result)
    }

    @Test
    fun `add can successfully add a book`() {
        val repo = BookRepo(books)
        val book = Book(UUID.randomUUID(), "me", "Book 2", "1234-999", randomStatus())
        val result = repo.add(book)
        assertEquals(Success(Unit), result)
    }

    @Test
    fun `getByISBN should find a single match`() {
        val repo = BookRepo(books)
        val result = repo.getByISBN("1234-222")
        assertEquals(book1, result)
    }

    @Test
    fun `getByISBN returns null when no match is found`() {
        val repo = BookRepo(books)
        val result = repo.getByISBN("dude")
        assertEquals(null, result)
    }

    @Test
    fun `findByTitle should match titles containing the search term`() {
        val repo = BookRepo(books)
        val result = repo.findByTitle("BooK")
        assertEquals(setOf(book1, book2, book3, book4), result.toSet())
    }

    @Test
    fun `findByTitle should match all results`() {
        val repo = BookRepo(books)
        val result = repo.findByTitle("Book 1")
        assertEquals(setOf(book2, book4), result.toSet())
    }

    @Test
    fun `findByTitle is case insensitive`() {
        val repo = BookRepo(books)
        val result = repo.findByTitle("bOoK 2")
        assertEquals(listOf(book3), result)
    }

    @Test
    fun `findByTitle returns no results when no matches are found`() {
        val repo = BookRepo(books)
        val result = repo.findByTitle("dude")
        assertEquals(0, result.size)
    }

    @Test
    fun `findByAuthor should match authors containing the search term`() {
        val repo = BookRepo(books)
        val result = repo.findByAuthor("BOB")
        assertEquals(setOf(book1, book2, book3), result.toSet())
    }

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