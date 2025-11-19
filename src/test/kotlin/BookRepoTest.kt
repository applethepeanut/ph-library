package com.atpfury.phlibrary.kotlin

import com.atpfury.phlibrary.kotlin.BookRepo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class BookRepoTest {

    @Test
    fun `findByAuthor returns no results when no matches are found`() {
        val repo = BookRepo
        val result = repo.findByAuthor("title")
        assertEquals(0, result.size)
    }
}