package com.bookStorage.core.models

import com.bookStorage.core.dto.OrderModelDto
import com.bookStorage.core.dto.OrderPartModelDto
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "order_part")
class OrderPartModel(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID(0,0),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    var book: BookModel = BookModel(), // отправляемая книга

    @Column(name = "book_count")
    var bookCount: Int = 0, //Сколько этой книги отправляем

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    var orderId: OrderModel = OrderModel() // к какому заказу имеет отношение
) {
    fun toDto(): OrderPartModelDto = OrderPartModelDto(
        id,
        book.toDto(),
        bookCount,
        orderId.id
    )
}