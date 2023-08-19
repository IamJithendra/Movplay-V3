package com.jvktech.moviebuff.data.local.db.movie

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jvktech.moviebuff.data.model.movie.MovieFavourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesMoviesDao {
    @Query("SELECT * FROM MovieFavourite ORDER BY added_date DESC")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieFavourite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeMovie(vararg movieDetails: MovieFavourite)

    @Query("DELETE FROM MovieFavourite WHERE id = :movieId")
    suspend fun unlikeMovie(movieId: Int)

    @Query("SELECT id FROM MovieFavourite")
    fun favouriteMoviesIds(): Flow<List<Int>>

    @Query("SELECT COUNT(id) FROM MovieFavourite")
    fun favouriteMoviesCount(): Flow<Int>
}