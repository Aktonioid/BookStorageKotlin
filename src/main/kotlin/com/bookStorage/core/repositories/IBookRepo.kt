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
    fun GetBooksByAuthorSearch(name: String): List<BookModel>
    fun GetBooksByAuthorByPage(name: String, page: Int, pageSize: Int): List<BookModel>
    fun GetBooksByGenre(genre: GenreModel, pageSize: Int, page: Int): List<BookModel>
    fun GetBookByName(name: String, pageSize: Int, page: Int): List<BookModel>
}