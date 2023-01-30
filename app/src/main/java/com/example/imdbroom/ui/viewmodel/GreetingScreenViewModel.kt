package com.example.imdbroom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.system.exitProcess

class GreetingScreenViewModel() : ViewModel() {

    private val _verify = MutableLiveData<Boolean>()
    val verify: LiveData<Boolean> = _verify

    fun checkIfLoginScreenPassed() {

        // in this function i can try to create custom login form with a separate fragment
        // if verification is correct it will forward to main screen
        _verify.postValue(true)
    }
    fun logout() {
        this@GreetingScreenViewModel
        exitProcess(0)//get out of app
    }


}