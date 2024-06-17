package com.bookStorage.core.models

import com.bookStorage.core.dto.SupplyModelDto
import com.bookStorage.core.dto.SupplyPartModelDto
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "supply_part")
class SupplyPartModel(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID(0,0),

    // тут связь в формате many to one, потому что supplyPartModel
    //используется в сете и в разных заказах могут быть одинаковые книги
    // и подтягиваться модель книги может не единожды
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    var book: BookModel = BookModel(),

    @ManyToOne
    @JoinColumn(name = "supply_id", nullable = false)
    @JsonIgnore
    var supplyId: SupplyModel = SupplyModel(),
    @Column(name = "book_count")
    var bookCount: Int = 0 //Сколько книг в поставке
) {
    fun toDto(): SupplyPartModelDto = SupplyPartModelDto(
        this.id,
        this.book.toDto(),
        supplyId.id,
        bookCount
    )
}