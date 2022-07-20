package com.example.androidfinalproject.di

import android.content.Context
import com.example.androidfinalproject.data.loacal_db.AppDatabase
import com.example.androidfinalproject.data.remote_db.CocktailService
import com.example.androidfinalproject.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson : Gson) : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    fun provideCocktailService(retrofit: Retrofit) : CocktailService =
        retrofit.create(CocktailService::class.java)



    @Provides
    @Singleton
    fun provideLocalDataBase(@ApplicationContext appContext : Context) : AppDatabase =
        AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideCocktailDao(database: AppDatabase) = database.cocktailDao()

    @Provides
    @Singleton
    fun provideFavoriteCocktailDao(database: AppDatabase) = database.favoriteCocktailDao()
}