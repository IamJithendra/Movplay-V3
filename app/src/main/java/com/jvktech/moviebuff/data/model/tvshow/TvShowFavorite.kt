package com.jvktech.moviebuff.data.model.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jvktech.moviebuff.data.model.Presentable
import java.util.*

@Entity
data class TvShowFavorite(
    @PrimaryKey
    override val id: Int,
    @ColumnInfo(name = "poster_path")
    override val posterPath: String?,
    val name: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Date,
    override val voteAverage: Float,
    override val voteCount: Int
) : Presentable {
    @Transient
    override val title: String = name
}
