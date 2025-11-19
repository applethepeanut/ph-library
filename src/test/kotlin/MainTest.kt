package com.atpfury.phlibrary.kotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class MainTest {

    @Test
    fun addTest() {
        val calc = Calculator()
        assertEquals(5, calc.add(2, 3))
    }
}