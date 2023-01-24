package com.example.imdbroom.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MoviesModel(
    @SerialName("results")
    val moviesResults: List<MoviesResult>
)

@Serializable
data class MoviesResult(
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdrop_path: String,
    @SerialName("id")
    val id: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("vote_average")
    val vote_average: Float,
    @SerialName("poster_path")
    val poster_path: String,
    @SerialName("release_date")
    val release_date: String,
    @SerialName("title")
    val title: String
)

