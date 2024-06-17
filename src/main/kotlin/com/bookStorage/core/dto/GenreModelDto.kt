package com.bookStorage.core.dto

import com.bookStorage.core.models.GenreModel
import jakarta.persistence.*
import java.util.UUID

data class GenreModelDto(
    var id: UUID,
    var name: String
) {
    fun toEntity(): GenreModel = GenreModel(id,name)
}