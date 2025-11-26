package com.atpfury.phlibrary

import java.util.*

class App {

    fun run(books: MutableList<Book>) {
        val libraryService = LibraryService()
        libraryService.addBooks(*books.toTypedArray())

        val author = "author 3"
        println("Books by author [$author]:")
        libraryService.findBooksByAuthor(author).map { println(it) }

        val title = "Title 1"
        println("Books by title [$title]:")
        libraryService.findBooksByAuthor(title).map { println(it) }

        val isbn = "5001"
        println("Books by ISBN [$isbn]:")
        println(libraryService.getBookByISBN(isbn))

        val book = books.first()
        println("Borrow book [$book] - this functionality is incomplete!")
        println(libraryService.borrowBook(book))
    }

}

fun main() {
    val books = mutableListOf(
        Book(UUID.randomUUID(), "Author 3", "Title 1", "3001", BookStatus.BORROWED),
        Book(UUID.randomUUID(), "Author 3", "Title 2", "3002", BookStatus.AVAILABLE),
        Book(UUID.randomUUID(), "Author 3", "Title 3", "3003", BookStatus.BORROWED),
        Book(UUID.randomUUID(), "Author 1", "title 1", "1001", BookStatus.AVAILABLE),
        Book(UUID.randomUUID(), "Author 1", "Title 2", "1002", BookStatus.AVAILABLE),
        Book(UUID.randomUUID(), "Author 2", "Title 1", "2001", BookStatus.AVAILABLE),
        Book(UUID.randomUUID(), "Author 4", "Title 1", "4001", BookStatus.AVAILABLE),
        Book(UUID.randomUUID(), "Author 5", "Title 1", "5001", BookStatus.AVAILABLE),
    )
    App().run(books)
}