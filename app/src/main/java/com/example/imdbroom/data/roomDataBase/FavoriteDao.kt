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

    @Query("Delete from ${Constants.dataBaseName.constantName} where id=:id")
    suspend fun deleteMovieById(id: Int)

    @Query("Select * from ${Constants.dataBaseName.constantName}")
    suspend fun getAllFavorites(): List<MovieDataClassDB>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constants.dataBaseName.constantName} WHERE id = :id)")
    suspend fun favoriteExist(id: Int): Boolean

    @Query("SELECT * FROM ${Constants.dataBaseName.constantName} WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchDataBase(searchQuery: String) : List<MovieDataClassDB>
}