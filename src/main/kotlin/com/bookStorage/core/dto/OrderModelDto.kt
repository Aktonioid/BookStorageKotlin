package com.bookStorage.core.dto

import com.bookStorage.core.models.OrderModel
import java.util.Date
import java.util.UUID

data class OrderModelDto(

    var id:UUID,

    var books: MutableSet<OrderPartModelDto>,

    var isSent: Boolean,

    var sendDate: Date,

    var userFullName: String,

    var deliveryAdress:String,// адрес доставки\

    var paymentStatus: Boolean // Оплачен ли заказ

) {
    fun toEntity(): OrderModel = OrderModel(
        id,
        books.map { it.toEntity() }.toMutableSet(),
        isSent,
        sendDate,
        userFullName,
        deliveryAdress,
        paymentStatus
    )
}