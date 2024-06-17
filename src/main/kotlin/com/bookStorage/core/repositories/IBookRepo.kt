package com.bookStorage.core.repositories

import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.GenreModel
import java.util.UUID

interface IBookRepo {
    fun CreateBookModel(bookModel: BookModel): Boolean
    fun UpdateBookModel(bookModel: BookModel): Boolean
    fun DeleteBookModel(id: UUID): Boolean
    fun GetAllBooksByPage(bookPage: Int, pageSize: Int): List<BookModel>
    fun GetBookById(id: UUID): BookModel
    fun GetBooksByAuthorSearch(name: String)
    fun GetBooksByAuthorByPage(name: String, page: Int, pageSize: Int)
    fun GetBooksByGenre(genre: GenreModel): List<BookModel>
    fun GetBookByName(name: String): List<BookModel>
}