package com.example.imdbroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbroom.data.roomDataBase.FavoriteDao
import com.example.imdbroom.data.model.MovieDataClassDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieDataClassDB>>()
    val movies: LiveData<List<MovieDataClassDB>> = _movies

    //favorite fragment is working with room database
    //some error persists as i have to tap 2 times to like/delete a favorite item


     fun listFavorites(){
         viewModelScope.launch(Dispatchers.IO){
             _movies.postValue(favoriteDao.getAllFavorites()) // reference to dao
         }
    }

    fun deleteFavorite(movie: MovieDataClassDB) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.removeMovie(movie)
        }
    }

    fun searchPostsTitleContains(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movies.postValue(favoriteDao.searchDataBase(searchString))
            } catch (e: NullPointerException) {
            }
        }
    }

}