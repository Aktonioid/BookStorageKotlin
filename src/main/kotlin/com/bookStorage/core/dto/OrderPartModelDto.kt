package com.bookStorage.core.dto

import com.bookStorage.core.models.OrderModel
import com.bookStorage.core.models.OrderPartModel
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

data class OrderPartModelDto(

    var id: UUID,

    var book: BookModelDto, // отправляемая книга

    var bookCount: Int, //Сколько этой книги отправляем

    var orderId: UUID // к какому заказу имеет отношение
) {
    fun toEntity(): OrderPartModel = OrderPartModel(
        id,
        book.toEntity(),
        bookCount,
        OrderModel(orderId)
    )
}