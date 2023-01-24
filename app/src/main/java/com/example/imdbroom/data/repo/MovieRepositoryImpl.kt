package com.example.imdbroom.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imdbroom.data.api.Api
import com.example.imdbroom.data.model.MoviesResult
import com.example.imdbroom.data.paging.MoviePaging
import com.example.imdbroom.data.paging.SearchPaging
import com.example.imdbroom.data.paging.TrendingPaging
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val service: Api) : MovieRepository {

    override fun getMovieList(filter: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            MoviePaging(service, filter)
        }.flow
        return request
    }

    override suspend fun getMovieById(id: Int): MoviesResult? {
        val request = service.getMovieById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    override fun getTrendingMovies(): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            TrendingPaging(service)
        }.flow
        return request
    }

    override fun getSearch(name: String): Flow<PagingData<MoviesResult>> {
        val request = Pager(PagingConfig(pageSize = 1)) {
            SearchPaging(service, name)
        }.flow
        return request
    }
}