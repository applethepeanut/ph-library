package com.atpfury.phlibrary.kotlin


interface LibraryServiceInterface {
    fun findBooksByAuthor(author: String): List<Book>
    fun addBooks(vararg books: Book): Result<List<String>>
}

object LibraryService : LibraryServiceInterface {

    private val bookRepo: BookRepoInterface = BookRepo()

    override fun addBooks(vararg books: Book): Result<List<String>> {
        val ops = books.map { bookRepo.add(it) }
        val failures = ops.filterIsInstance<Failure<String>>().map { it.reason }

        return if (failures.isEmpty()) Success else Failure(failures)
    }

    override fun findBooksByAuthor(author: String): List<Book> {
        return bookRepo.findByAuthor(author)
    }

}