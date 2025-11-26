package com.atpfury.phlibrary

interface LibraryServiceInterface {
    fun findBooksByAuthor(author: String): List<Book>
    fun findBooksByTitle(title: String): List<Book>
    fun getBookByISBN(isbn: String): Book?
    fun addBooks(vararg books: Book): Result<List<String>, Unit>
    fun borrowBook(book: Book): Result<String, Book>
}

class LibraryService(private val bookRepo: BookRepo = BookRepo()) : LibraryServiceInterface {

    override fun borrowBook(book: Book): Result<String, Book> {
        return when (val result = bookRepo.get(book.id)) {
            is Success ->
                if(book.status == BookStatus.AVAILABLE) {
                    //TODO bookRepo.upddate(book.copy(status = BookStatus.BORROWED))
                    Success(book)
                }
                else Failure("Book ${book.id} is not available.")
            is Failure -> result
        }
    }

    override fun addBooks(vararg books: Book): Result<List<String>, Unit> {
        val ops = books.map { bookRepo.add(it) }
        val failures = ops.filterIsInstance<Failure<String>>().map { it.value }

        return if (failures.isEmpty()) Success(Unit) else Failure(failures)
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