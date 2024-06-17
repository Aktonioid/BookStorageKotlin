package com.bookStorage.infrastucture.controllers

import com.bookStorage.core.dto.BookModelDto
import com.bookStorage.core.dto.GenreModelDto
import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.GenreModel
import com.bookStorage.core.repositories.IBookRepo
import com.bookStorage.core.repositories.IGenreRepo
import com.bookStorage.core.services.IGenreService
import jakarta.validation.ConstraintViolation
import jakarta.validation.Valid
import jakarta.validation.Validation
import jakarta.validation.Validator
import jakarta.validation.ValidatorFactory
import org.springframework.http.ResponseEntity
import java.util.UUID
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class TestController(val genreRepo: IGenreRepo, val genreService: IGenreService, val bookRepo: IBookRepo) {

    @PostMapping("/create")
    fun createGenre(@RequestBody genreModel: GenreModel): ResponseEntity<Boolean>{

//        genreModel.id = UUID.randomUUID()
        val result: Boolean = genreRepo.CreateGenre(genreModel)

        return ResponseEntity.ok(result)
    }



    @GetMapping("/{id}")
    fun getGenre(@PathVariable("id") id: UUID): ResponseEntity<GenreModel>{

        return ResponseEntity.ok(genreRepo.GetGenreById(id))
    }

    @GetMapping
    fun getGenres(): ResponseEntity<List<GenreModel>>{
        return ResponseEntity.ok(genreRepo.GetAllGenres())
    }

    @DeleteMapping("/{id}")
    fun deleteGenre(id: UUID): ResponseEntity<Boolean>{
        return ResponseEntity.ok(genreRepo.DeleteGenresById(id))
    }

    @PutMapping()
    fun updateGenre(@RequestBody genreModel: GenreModel): ResponseEntity<Boolean>{
        return ResponseEntity.ok(genreRepo.UpdateGenre(genreModel))
    }

    @PostMapping("/createDto")
    suspend  fun createFromDto(@RequestBody genreModelDto: GenreModelDto): Boolean =
        coroutineScope {
            println("coroutine executes in thread named: ${Thread.currentThread().name}")
            val tst = async {
                delay(4000)
                println("coroutine executes after delay in thread named: ${Thread.currentThread().name}")
                genreService.CreateGenre(genreModelDto)
            }
            tst.await()
        }




    @PostMapping("/testShit")
    suspend fun createTest(@Valid @RequestBody bookModel: BookModel): Boolean = coroutineScope {

        val model: BookModel = BookModel()

        val validationFactory: ValidatorFactory = Validation.buildDefaultValidatorFactory()

        val validator: Validator = validationFactory.validator


        val v: MutableSet<ConstraintViolation<BookModel>> = validator.validate(bookModel)

        println(bookModel.isbn)
        println(bookModel.leftovers)
        println(v.size)

        false
    }


    @PostMapping("/testShitt")
    suspend fun createTestt(@Valid @RequestBody bookModel: BookModel): Boolean = coroutineScope {

        bookRepo.CreateBookModel(bookModel)
    }
}