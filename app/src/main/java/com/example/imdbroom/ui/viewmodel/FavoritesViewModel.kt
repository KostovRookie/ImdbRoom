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


     fun listFavorites(){
         viewModelScope.launch(Dispatchers.IO){
             _movies.postValue(favoriteDao.getAllFavorites())
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