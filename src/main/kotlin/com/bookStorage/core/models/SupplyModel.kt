package com.bookStorage.core.models

import com.bookStorage.core.dto.SupplyModelDto
import jakarta.persistence.*
import java.util.Date
import java.util.UUID

@Entity
@Table(name = "supply")
class SupplyModel(

    @Id // не GeneratedValue, так как генериться они будут при создании модели
    var id: UUID = UUID(0,0),

    @OneToMany
    @JoinColumn(name = "supply_id")
    var books: MutableSet<SupplyPartModel> = mutableSetOf(),//поставляемые книги

    var provider: String ="", // поставщик

    @Column(name = "expected_delivery_date")
    @Basic
    @Temporal(TemporalType.DATE)
    var expectedDeliveryDate:Date = Date(),// дата в которую поставка ожидается

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "real_delivery_date")
    var realDeliveryDate: Date = Date(),//реальная дата поставки

    @Column(name = "is_delivery_arrived")
    val isArived: Boolean=false
) {
    fun toDto(): SupplyModelDto = SupplyModelDto(
        id,
        books.map { it.toDto() }.toMutableSet(),
        provider,
        expectedDeliveryDate,
        realDeliveryDate,
        isArived
    )
}