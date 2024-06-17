package com.bookStorage.infrastucture.controllers

import com.bookStorage.core.dto.GenreModelDto
import com.bookStorage.infrastucture.services.GenreService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/genres")
class GenreController(val genreService: GenreService) {

    @PostMapping("/")
    suspend fun CreateGenre(@RequestBody genreModelDto: GenreModelDto): Boolean = coroutineScope{
        genreService.CreateGenre(genreModelDto)
    }

    @PutMapping("/")
    suspend fun UpdateGenre(@RequestBody genreModelDto: GenreModelDto): Boolean = coroutineScope {
        genreService.UpdateGenre(genreModelDto)
    }

    @DeleteMapping("/{id}")
    suspend fun DeleteGenre(@PathVariable("id") id: UUID): Boolean = coroutineScope {
        genreService.DeleteGnereById(id)
    }

    @GetMapping("/")
    suspend fun GetAllGneres(): List<GenreModelDto> = coroutineScope {
        genreService.GetAllGenres()
    }

    @GetMapping("/{id}")
    suspend fun GetGerneById(@PathVariable("id") id: UUID): GenreModelDto = coroutineScope {
        genreService.GetGenreById(id)
    }
}