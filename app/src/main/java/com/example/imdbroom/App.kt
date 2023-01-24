package com.example.imdbroom

import android.app.Application
import com.example.imdbroom.data.di.*
import com.example.imdbroom.ui.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
        }
        KoinModule.load()
        DataModule.load()

    }
}