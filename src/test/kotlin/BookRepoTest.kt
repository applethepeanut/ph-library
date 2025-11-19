package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BookRepoTest {

    @Test
    fun `findByAuthor returns no results when no matches are found`() {
        val repo = BookRepo()
        val result = repo.findByAuthor("title")
        assertEquals(0, result.size)
    }
}