package com.bookStorage.core.services

import com.bookStorage.core.dto.GenreModelDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface IGenreService {
    suspend fun CreateGenre(genre: GenreModelDto): Boolean
    suspend fun UpdateGenre(genre: GenreModelDto): Boolean
    suspend fun DeleteGnereById(genreId:UUID): Boolean
    suspend fun GetGenreById(genreId:UUID): GenreModelDto
    suspend fun GetAllGenres(): List<GenreModelDto>
}