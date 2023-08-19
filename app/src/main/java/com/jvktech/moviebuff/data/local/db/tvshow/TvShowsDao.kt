package com.jvktech.moviebuff.data.local.db.tvshow

import androidx.paging.PagingSource
import androidx.room.*
import com.jvktech.moviebuff.data.model.tvshow.TvShowEntity
import com.jvktech.moviebuff.data.model.tvshow.TvShowEntityType
import com.jvktech.moviebuff.utils.TvShowEntityTypeConverters

@TypeConverters(TvShowEntityTypeConverters::class)
@Dao
interface TvShowsDao {
    @Query("SELECT * FROM TvShowEntity WHERE type=:type AND language=:language")
    fun getAllTvShows(type: TvShowEntityType, language: String): PagingSource<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTvShow(movies: List<TvShowEntity>)

    @Query("DELETE FROM TvShowEntity WHERE type=:type AND language=:language")
    suspend fun deleteTvShowsOfType(type: TvShowEntityType, language: String)
}