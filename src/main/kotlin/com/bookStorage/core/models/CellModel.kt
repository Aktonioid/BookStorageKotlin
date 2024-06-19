package com.bookStorage.core.models

import com.bookStorage.core.dto.CellModelDto
import jakarta.persistence.*
import org.apache.poi.ss.usermodel.Cell
import java.util.UUID

@Entity
@Table(name = "cell")
class CellModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,

    //Книга, которая лежит в этой ячейке
    // в книгах надо прописать в остатках, что максимум может быть штук 900
    @JoinColumn(name = "book_id")
    @ManyToOne(fetch = FetchType.EAGER)
//    @Column(name="book_id")
    var bookId: BookModel = BookModel()
) {
    fun toDto(): CellModelDto = CellModelDto(this.id, this.bookId.toDto())
}