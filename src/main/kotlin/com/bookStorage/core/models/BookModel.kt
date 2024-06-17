package com.bookStorage.core.models

import com.bookStorage.core.dto.BookModelDto
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.util.UUID

@Entity
@Table(name = "book")
class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID(0,0),

    @Column(name = "book_name")
    var bookName: String = "",

    @Column(name = "author_name")
    var authorName: String = "",

    @Column
    var publisher: String ="",

    @Column
    var price: Int = -1,

    @ManyToMany(fetch = FetchType.LAZY)
    var genres: MutableSet<GenreModel> = mutableSetOf(),

    @Column
    @field:Pattern(regexp = "\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d")
    var isbn: String = "",

    @Column
    var description: String = "",

    @Column(name = "pic_url")
    var picUrl: String = "",

    @Column(name = "leftovers")
    @field:Min(value = 0, message = "must be equals or above 0")
    @field:Max(value = 900, message = "must not be above 900")
    var leftovers: Short = -1// TODO сделать так, чтобы было максимум 900 книг
)
{
//    constructor(){}
    fun toDto(): BookModelDto = BookModelDto(
        this.id,
        this.bookName,
        this.authorName,
        this.publisher,
        this.price,
        this.isbn,
        this.genres.map { it.toDto() }.toMutableSet(),
        this.description,
        this.picUrl,
        this.leftovers
    )
}