package com.bookStorage.core.models

import com.bookStorage.core.dto.OrderModelDto
import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "orders")
class OrderModel(

    @Id
    var id:UUID = UUID(0,0),

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    var books: MutableSet<OrderPartModel> = mutableSetOf(),

    @Column(name = "is_sent")
    var isSent: Boolean = false,

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="send_date")
    var sendDate: Date = Date(),

    @Column(name = "user_full_name")
    var userFullName: String = "",

    @Column(name = "delivery_adress")
    var deliveryAdress:String ="",// адрес доставки\

    @Column(name = "payment_status")
    var paymentStatus: Boolean = false // Оплачен ли заказ

) {
    fun toDto(): OrderModelDto = OrderModelDto(
        this.id,
        this.books.map { it.toDto() }.toMutableSet(),
        this.isSent,
        this.sendDate,
        this.userFullName,
        this.deliveryAdress,
        this.paymentStatus
    )
}