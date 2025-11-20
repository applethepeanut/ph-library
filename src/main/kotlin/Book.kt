package com.atpfury.phlibrary.kotlin

enum class BookStatus {
    AVAILABLE,
    BORROWED
}

data class Book(val author: String, val title: String, val isbn: String, val status: BookStatus)