package com.bookStorage.core.dto

import com.bookStorage.core.models.SupplyModel
import com.bookStorage.core.models.SupplyPartModel
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

data class SupplyPartModelDto(

    var id: UUID,

    var book: BookModelDto,

    var supplyId: UUID,
    var bookCount: Int //Сколько книг в поставке
) {
    fun toEntity(): SupplyPartModel = SupplyPartModel(
        id,
        book.toEntity(),
        SupplyModel(supplyId),
        bookCount
    )
}