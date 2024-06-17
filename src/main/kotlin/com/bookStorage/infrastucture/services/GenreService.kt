package com.bookStorage.infrastucture.services

import com.bookStorage.core.dto.GenreModelDto
import com.bookStorage.core.repositories.IGenreRepo
import com.bookStorage.core.services.IGenreService
import org.springframework.stereotype.Service
import java.util.*

@Service
class GenreService(val genreRepo:IGenreRepo): IGenreService {

    override suspend fun CreateGenre(genre: GenreModelDto): Boolean {
        genre.id = UUID(0L, 0L)
        return genreRepo.CreateGenre(genre.toEntity())
    }

    override suspend fun UpdateGenre(genre: GenreModelDto): Boolean {
        return genreRepo.UpdateGenre(genre.toEntity())
    }

    override suspend fun DeleteGnereById(genreId: UUID): Boolean {
        return genreRepo.DeleteGenresById(genreId)
    }

    override suspend fun GetGenreById(genreId: UUID): GenreModelDto {
        return genreRepo.GetGenreById(genreId).toDto()
    }

    override suspend fun GetAllGenres(): List<GenreModelDto> {
        return genreRepo.GetAllGenres().map { it.toDto() }
    }

}