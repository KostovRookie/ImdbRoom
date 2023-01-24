package com.example.imdbroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbroom.data.model.MovieDataClassDB
import com.example.imdbroom.data.model.MoviesResult
import com.example.imdbroom.data.roomDataBase.FavoriteDao
import com.example.imdbroom.data.repo.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val favoriteDao: FavoriteDao,
    private val repository: MovieRepositoryImpl
) : ViewModel() {


    private var _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _similar = MutableLiveData<List<MoviesResult>>()

    private val _movieDetails = MutableLiveData<MoviesResult>()
    val movieDetails: MutableLiveData<MoviesResult> = _movieDetails



    val errorMessage = MutableLiveData<String>()

    val progressBar = MutableLiveData<Boolean>()

    var favorite = MutableLiveData(false)

    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getMovieById(id)
            _movieDetails.postValue(result!!)
        }
    }



    fun favoriteMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetails.value!!.apply {
                val movieDataClassDB = MovieDataClassDB(id, poster_path, vote_average, title, backdrop_path)
                if (favorite.value == true) {
                    favoriteDao.removeMovie(movieDataClassDB)
                } else {
                    favoriteDao.save(movieDataClassDB)
                }
            }
        }
    }

    fun checkFavorite() {
        viewModelScope.launch(Dispatchers.IO){
            val response = favoriteDao.favoriteExist(movieDetails.value!!.id)
            favorite.postValue(response)
        }
    }
}