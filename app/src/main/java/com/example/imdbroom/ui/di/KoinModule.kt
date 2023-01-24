package com.example.imdbroom.ui.di

import com.example.imdbroom.ui.viewmodel.FavoritesViewModel
import com.example.imdbroom.ui.viewmodel.MainViewModel
import com.example.imdbroom.ui.viewmodel.DetailsViewModel
import com.example.imdbroom.ui.viewmodel.GreetingScreenViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinModule {

    fun load() {
        loadKoinModules( movieListModule() + overviewModule() + favoriteModule() + loginModule())
    }

    private fun loginModule() : Module{
        return module {
            factory { GreetingScreenViewModel()  }
        }
    }

    private fun movieListModule(): Module {
        return module {
            factory { MainViewModel(get()) }
        }
    }

    private fun overviewModule(): Module {
        return module {
            factory { DetailsViewModel(get(), get()) }
        }
    }

    private fun favoriteModule(): Module {
        return module {
            factory { FavoritesViewModel(get()) }
        }
    }
}