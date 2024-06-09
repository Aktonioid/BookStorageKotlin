package com.bookStorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookStorageApplication

fun main(args: Array<String>) {
	runApplication<BookStorageApplication>(*args)
}
