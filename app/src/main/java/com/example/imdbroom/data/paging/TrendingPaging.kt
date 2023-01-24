package com.example.imdbroom.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imdbroom.data.api.Api
import com.example.imdbroom.data.model.MoviesResult
import com.example.imdbroom.utils.Constants
import java.lang.Exception

class TrendingPaging(private val service: Api) :
    PagingSource<Int, MoviesResult>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesResult> {

        return try {
            val currentPage = params.key ?: Constants.PAGE.DEFAULT_PAGE
            val response = service.getTrendingMovies(currentPage)
            val responseData = mutableListOf<MoviesResult>()
            val data = response.body()?.moviesResults ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (data.isEmpty()) null else currentPage.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}