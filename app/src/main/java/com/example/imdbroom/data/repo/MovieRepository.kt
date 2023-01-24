package com.example.imdbroom.data.repo

import androidx.paging.PagingData
import com.example.imdbroom.data.model.MoviesResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(filter: String): Flow<PagingData<MoviesResult>>

    suspend fun getMovieById(id: Int): MoviesResult?

    fun getTrendingMovies(): Flow<PagingData<MoviesResult>>

    fun getSearch(name: String): Flow<PagingData<MoviesResult>>

}