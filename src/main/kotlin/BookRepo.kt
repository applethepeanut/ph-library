package com.atpfury.phlibrary.kotlin


interface BookRepoInterface {

    fun add(book: Book): Result<String>
    fun findByAuthor(author: String): List<Book>
    fun findByTitle(title: String): List<Book>
    fun getByISBN(isbn: String): Book?
}

data class BookRepo(val books: MutableList<Book> = mutableListOf()) : BookRepoInterface {

    override fun add(book: Book): Result<String> {
        if (books.any { it.id == book.id }) {
            return Failure("The book ${book.id} already exists.")
        } else {
            books.add(book)
            return Success
        }
    }

    private fun findBy(propertySelector: (Book) -> String, term: String): List<Book> =
        books.filter { (propertySelector(it)).lowercase().contains(term.lowercase()) }

    override fun findByAuthor(author: String): List<Book> {
        return findBy({ it.author }, author)
    }

    override fun findByTitle(title: String): List<Book> {
        return findBy({ it.title }, title)
    }

    override fun getByISBN(isbn: String): Book? {
        return books.find { it.isbn == isbn }
    }
}