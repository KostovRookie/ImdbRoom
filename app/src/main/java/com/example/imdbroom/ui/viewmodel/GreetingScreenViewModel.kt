package com.example.imdbroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GreetingScreenViewModel() : ViewModel() {

    private val _verify = MutableLiveData<Boolean>()
    val verify: LiveData<Boolean> = _verify

    fun checkIfUserIsLogger() {
        _verify.postValue(true)
    }
    fun logout() {
    }


}