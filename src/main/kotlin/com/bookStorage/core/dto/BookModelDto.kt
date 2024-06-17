package com.bookStorage.core.dto

import com.bookStorage.core.models.BookModel
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import java.util.UUID

data class BookModelDto(

    var id: UUID,

    var bookName: String,

    var authorName: String,

    var publisher: String,

    var price: Int,

    @field:Pattern(regexp = "\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d")
    var isbn: String,

    var genres: MutableSet<GenreModelDto>,

    var description: String,

    var picUrl: String,

    @field:Min(value = 0, message = "must be equals or above 0")
    @field:Max(value = 900, message = "must not be above 900")
    var leftovers: Short// TODO сделать так, чтобы было максимум 900 книг

) {
    fun toEntity(): BookModel = BookModel(
        id,
        bookName,
        authorName,
        publisher,
        price,
        genres.map { it.toEntity() }.toMutableSet(),
        isbn,
        description,
        picUrl,
        leftovers
    )
}