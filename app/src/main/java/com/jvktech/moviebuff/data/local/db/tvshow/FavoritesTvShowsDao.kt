package com.jvktech.moviebuff.data.local.db.tvshow

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jvktech.moviebuff.data.model.tvshow.TvShowFavourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesTvShowsDao {
    @Query("SELECT * FROM TvShowFavourite ORDER BY added_date DESC ")
    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowFavourite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeTvShow(vararg tvShowDetails: TvShowFavourite)

    @Query("DELETE FROM TvShowFavourite WHERE id = :tvShowId")
    suspend fun unlikeTvShow(tvShowId: Int)

    @Query("SELECT id FROM TvShowFavourite")
    fun favoriteTvShowIds(): Flow<List<Int>>

    @Query("SELECT COUNT(id) FROM TvShowFavourite")
    fun favoriteTvShowCount(): Flow<Int>
}