package com.example.imdbroom.data.di

import android.util.Log
import com.example.imdbroom.data.api.Api
import com.example.imdbroom.data.roomDataBase.FavoriteDatabase
import com.example.imdbroom.data.repo.MovieRepositoryImpl
import com.example.imdbroom.utils.Constants
import com.example.imdbroom.utils.jsonDefaultInstance
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

object DataModule {

    private const val OK_HTTP= "Ok http"
    private val contentType = "application/json".toMediaType()
    fun load() {
        loadKoinModules(movieModule() + networkModule() + daoModule())
    }

    private fun movieModule(): Module {
        return module {
            single { MovieRepositoryImpl(get()) }

        }
    }



    private fun daoModule(): Module {
        return module {
            single { FavoriteDatabase.getDataBase(androidContext()).getFavoriteDao() }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun networkModule(): Module {
        return module {

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it )
                }

                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                Retrofit.Builder()
                    .baseUrl(Constants.URL.BASE_URL)
                    .addConverterFactory(jsonDefaultInstance.asConverterFactory(contentType))
                    .build()
                    .create(Api::class.java)
            }

        }
    }
}