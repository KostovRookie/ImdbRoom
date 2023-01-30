package com.example.imdbroom.utils

class Constants private constructor(){
    object URL{
        const val BASE_URL = "https://api.themoviedb.org"
        const val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    }
    object apiKey{
        const val KEY = "33260b6f48a3b9c38633e3a5cb1c9443"
    }
    object filterMovies {
        const val MOVIEFILTER = "moviefilter"
    }
    object dataBaseName{
        const val constantName = "StupidDatabase"
    }
    object PAGE{
        const val DEFAULT_PAGE = 1
    }
}

