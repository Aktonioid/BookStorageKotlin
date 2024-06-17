package com.bookStorage.core.dto

import com.bookStorage.core.models.CellModel

data class CellModelDto(

    var id: Int,

    //Книга, которая лежит в этой ячейке
    // в книгах надо прописать в остатках, что максимум может быть штук 900
    var bookId: BookModelDto
) {

    fun toEntity(): CellModel = CellModel(id, bookId.toEntity())
}