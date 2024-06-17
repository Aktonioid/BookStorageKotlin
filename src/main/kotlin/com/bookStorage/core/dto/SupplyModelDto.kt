package com.bookStorage.core.dto

import com.bookStorage.core.models.SupplyModel
import java.util.Date
import java.util.UUID

data class SupplyModelDto(

    var id: UUID,

    var books: MutableSet<SupplyPartModelDto>,//поставляемые книги

    var provider: String, // поставщик

    var expectedDeliveryDate:Date,// дата в которую поставка ожидается

    var realDeliveryDate: Date,//реальная дата поставки

    val isArived: Boolean
) {
    fun toEntity(): SupplyModel = SupplyModel(
        id,
        books.map { it.toEntity() }.toMutableSet(),
        provider,
        expectedDeliveryDate,
        realDeliveryDate,
        isArived
    )
}