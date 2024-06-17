package com.bookStorage.core.repositories

import com.bookStorage.core.models.GenreModel
import java.util.UUID

interface IGenreRepo {
    /**
    *Create genre model in database
    * @param GenreModel is required
    * */
    fun CreateGenre(genre: GenreModel): Boolean

    /**
     * Get genreModel by id
     * @param Id of entity is required> type of id is UUID
     */
    fun GetGenreById(id: UUID): GenreModel

    /**
     * Get  all genres
     */
    fun GetAllGenres(): List<GenreModel>

    /**
     *  Deletes genre from database
     *  @param id for removal from database
     */
    fun DeleteGenresById(id: UUID): Boolean

    /**
     *  Update genre model
     *  @param update record that already exhist in database
     */
    fun UpdateGenre(genre: GenreModel): Boolean
}