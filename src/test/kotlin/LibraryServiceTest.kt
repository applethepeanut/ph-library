package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class LibraryServiceTest {

    val book1 = Book("Author 3", "Title 1", "3001", BookStatus.BORROWED)
    val book2 = Book("Author 3", "Title 2", "3002", BookStatus.AVAILABLE)
    val book3 = Book("Author 3", "Title 3", "3003", BookStatus.BORROWED)

    interface Fixture {
        val libraryService: LibraryService
    }

    private fun fixture(): Fixture = object : Fixture {
        override val libraryService = LibraryService

        init {
            libraryService.addBooks(
                Book("Author 1", "title 1", "1001", BookStatus.AVAILABLE),
                Book("Author 1", "Title 2", "1002", BookStatus.AVAILABLE),
                Book("Author 2", "Title 1", "2001", BookStatus.AVAILABLE),
                Book("Author 4", "Title 1", "4001", BookStatus.AVAILABLE),
                Book("Author 5", "Title 1", "5001", BookStatus.AVAILABLE),
                book1, book2, book3
            )
        }
    }

    @Test
    fun `findBooksByTitle should return books by title`() {
        val fixture = fixture()
        val result = fixture.libraryService.findBooksByTitle("Title 3")
        assertEquals(1, result.size)
        assertEquals(book3, result.first())
    }

    @Test
    fun `findBooksByTitle should return no results when no books are found`() {
        val fixture = fixture()
        val result = fixture.libraryService.findBooksByTitle("some title")
        assertEquals(0, result.size)
    }

    @Test
    fun `findBooksByAuthor should return books by my favourite author`() {
        val fixture = fixture()
        val result = fixture.libraryService.findBooksByAuthor("Author 3")
        assertEquals(3, result.size)
        val (availableBooks, unAvailableBooks) = result.partition { it.status == BookStatus.AVAILABLE }
        assertEquals(true, availableBooks.contains(book2))
        assertEquals(true, unAvailableBooks.contains(book1))
        assertEquals(true, unAvailableBooks.contains(book3))
    }

    @Test
    fun `findBooksByAuthor should return no results when no books are found`() {
        val fixture = fixture()
        val result = fixture.libraryService.findBooksByAuthor("some person")
        assertEquals(0, result.size)
    }
}