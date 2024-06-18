package com.bookStorage.core.responeEntities

class BookResponse(
    var incorrectISBN: Boolean = false,
    var incorrectPrice: Boolean = false,
    var incorrectLeftovers:Boolean = false,
    var databaseError: Boolean = false,
    var notUniqueIsbn: Boolean = false

) {

}