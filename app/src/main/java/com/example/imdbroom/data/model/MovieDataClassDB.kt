package com.example.imdbroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imdbroom.utils.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.dataBaseName.constantName)
data class MovieDataClassDB(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Float,
    val title: String,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String
)