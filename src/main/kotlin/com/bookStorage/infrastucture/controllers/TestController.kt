package com.bookStorage.infrastucture.controllers

import com.bookStorage.core.dto.BookModelDto
import com.bookStorage.core.dto.GenreModelDto
import com.bookStorage.core.models.BookModel
import com.bookStorage.core.models.GenreModel
import com.bookStorage.core.repositories.IBookRepo
import com.bookStorage.core.repositories.ICellRepo
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
class TestController(val bookRepo: IBookRepo, val cellRepo: ICellRepo) {

    @PostMapping("/create")
    fun createTest(@RequestBody bookModel: BookModel): Boolean{
        bookModel.id=UUID(0L, 0L)
        return  bookRepo.CreateBookModel(bookModel)
    }

    @PutMapping("/update")
    fun updateTest(@RequestBody bookModel: BookModel):Boolean{

        val result = bookRepo.UpdateBookModel(bookModel)

        return result
    }

    @GetMapping("/get")
    fun getTest(): List<BookModel>{
        return bookRepo.GetAllBooksByPage(1,20)
    }

    @GetMapping("/{id}")
    fun getByIdTest(@PathVariable id: UUID): BookModel = bookRepo.GetBookById(id)

    @GetMapping("cell/{id}")
    fun CheckIfCellIsEmpty(id: Int): Boolean{

        return cellRepo.CheckIfEmptyCells(id)
    }


    @PostMapping("/testShitt")
    suspend fun createTestt(@Valid @RequestBody bookModel: BookModel): Boolean = coroutineScope {

        bookRepo.CreateBookModel(bookModel)
    }
}