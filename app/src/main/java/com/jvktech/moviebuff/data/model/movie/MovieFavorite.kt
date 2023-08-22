package com.jvktech.moviebuff.data.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jvktech.moviebuff.data.model.Presentable
import java.util.*

@Entity
data class MovieFavorite(
    @PrimaryKey
    override val id: Int,
    @ColumnInfo(name = "poster_path")
    override val posterPath: String?,
    override val title: String,
    @ColumnInfo(name = "original_title")
    val originalTitle: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Date,
    override val voteAverage: Float,
    override val voteCount: Int
) : Presentable
