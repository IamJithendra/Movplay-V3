package com.jvktech.moviebuff.data.local.db.movie

import androidx.paging.PagingSource
import androidx.room.*
import com.jvktech.moviebuff.data.model.movie.MovieEntity
import com.jvktech.moviebuff.data.model.movie.MovieEntityType
import com.jvktech.moviebuff.utils.MovieEntityTypeConverters

@TypeConverters(MovieEntityTypeConverters::class)
@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieEntity WHERE type=:type AND language=:language")
    fun getAllMovies(type: MovieEntityType, language: String): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM MovieEntity WHERE type=:type AND language=:language")
    suspend fun deleteMoviesOfType(type: MovieEntityType, language: String)
}