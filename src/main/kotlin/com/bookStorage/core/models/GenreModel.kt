package com.bookStorage.core.models

import com.bookStorage.core.dto.GenreModelDto
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name="genres")
class GenreModel(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID(0,0),
    @Column(name = "name")
    var name: String = ""
) {
    constructor(name: String) : this() {
        this.name = name
    }
    fun toDto(): GenreModelDto = GenreModelDto(
        this.id,
        this.name
    )
}