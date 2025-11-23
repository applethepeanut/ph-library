package com.atpfury.phlibrary.kotlin


interface LibraryServiceInterface {
    fun findBooksByAuthor(author: String): List<Book>
    fun findBooksByTitle(title: String): List<Book>
    fun getBookByISBN(isbn: String): Book?
    fun addBooks(vararg books: Book): Result<List<String>>
}

class LibraryService(private val bookRepo: BookRepo = BookRepo()) : LibraryServiceInterface {

    override fun addBooks(vararg books: Book): Result<List<String>> {
        val ops = books.map { bookRepo.add(it) }
        val failures = ops.filterIsInstance<Failure<String>>().map { it.reason }

        return if (failures.isEmpty()) Success else Failure(failures)
    }

    override fun findBooksByAuthor(author: String): List<Book> {
        return bookRepo.findByAuthor(author)
    }

    override fun findBooksByTitle(title: String): List<Book> {
        return bookRepo.findByTitle(title)
    }

    override fun getBookByISBN(isbn: String): Book? {
        return bookRepo.getByISBN(isbn)
    }

}