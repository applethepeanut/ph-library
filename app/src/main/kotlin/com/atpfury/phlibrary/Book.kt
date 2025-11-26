package com.atpfury.phlibrary

import java.util.UUID

enum class BookStatus {
    AVAILABLE,
    BORROWED
}

data class Book(val id: UUID, val author: String, val title: String, val isbn: String, val status: BookStatus)