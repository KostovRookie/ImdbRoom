package com.example.imdbroom.data.api

import com.example.imdbroom.utils.Constants
import com.example.imdbroom.data.model.MoviesModel
import com.example.imdbroom.data.model.MoviesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/3/movie/{type}?api_key=" + Constants.APIKEY.KEY)
    suspend fun getMovieList(
        @Path(value = "type") type: String,
        @Query("page") page: Int = 1
    ): Response<MoviesModel>

    @GET("/3/trending/movie/day?api_key=" + Constants.APIKEY.KEY)
    suspend fun getTrendingMovies
                (@Query("page") page: Int = 1)
    : Response<MoviesModel>

    @GET("/3/movie/{movie_id}?api_key=" + Constants.APIKEY.KEY)
    suspend fun getMovieById(
        @Path(
            value = "movie_id",
            encoded = true
        ) movie_id: Int
    ): Response<MoviesResult>


    @GET("/3/search/movie?api_key=" + Constants.APIKEY.KEY)
    suspend fun getSearch
                (@Query("query") name: String,
                 @Query("page") page: Int = 1)
    : Response<MoviesModel>

}
