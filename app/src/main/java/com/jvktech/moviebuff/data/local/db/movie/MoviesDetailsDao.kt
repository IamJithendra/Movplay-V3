package com.jvktech.moviebuff.data.local.db.movie

import androidx.paging.PagingSource
import androidx.room.*
import com.jvktech.moviebuff.data.model.movie.MovieDetailEntity
import com.jvktech.moviebuff.utils.MovieEntityTypeConverters

@TypeConverters(MovieEntityTypeConverters::class)
@Dao
interface MoviesDetailsDao {
    @Query("SELECT * FROM MovieDetailEntity WHERE language=:language")
    fun getAllMovies(language: String): PagingSource<Int, MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(movies: List<MovieDetailEntity>)

    @Query("DELETE FROM MovieDetailEntity WHERE language=:language")
    suspend fun deleteMovieDetails(language: String)
}