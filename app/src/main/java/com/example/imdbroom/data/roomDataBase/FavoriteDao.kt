package com.example.imdbroom.data.roomDataBase

import androidx.room.*
import com.example.imdbroom.utils.Constants
import com.example.imdbroom.data.model.MovieDataClassDB

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieDataClassDB)

    @Delete
    suspend fun removeMovie(movie: MovieDataClassDB)

    @Query("Delete from ${Constants.TABLE.NAME} where id=:id")
    suspend fun deleteMovieById(id: Int)

    @Query("Select * from ${Constants.TABLE.NAME}")
    suspend fun getAllFavorites(): List<MovieDataClassDB>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.TABLE.NAME} WHERE id = :id)")
    suspend fun favoriteExist(id: Int): Boolean

    @Query("SELECT * FROM ${Constants.TABLE.NAME} WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchDataBase(searchQuery: String) : List<MovieDataClassDB>
}