package com.bookStorage.core.controllers

import org.springframework.http.ResponseEntity
import java.util.UUID

// Временный интерфейс тупо чтоб было быстрее базового реализовывать конгтроллеры
interface IControllerBase<T> {

    fun create(model:T): ResponseEntity<Any>
    fun update(model:T): ResponseEntity<Any>
    fun getAll(): ResponseEntity<List<T>>
    fun getbyId(id: UUID): ResponseEntity<T>
    fun deleteById(id: UUID): ResponseEntity<Any>

}