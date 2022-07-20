package com.example.androidfinalproject.data.repository

import com.example.androidfinalproject.data.loacal_db.CocktailDao
import com.example.androidfinalproject.data.loacal_db.FavoriteCocktailDao
import com.example.androidfinalproject.data.models.FavoriteCocktail
import com.example.androidfinalproject.data.remote_db.CocktailRemoteDataSource
import com.example.androidfinalproject.utils.performFetching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteCocktailRepository @Inject constructor (
        private val localDataSource : FavoriteCocktailDao
    ){
        fun  getAllFavoriteCocktails() = performFetching {
            localDataSource.getAllFavoriteCocktails()
        }

        fun insertFavoriteCocktail(favoriteCocktail: FavoriteCocktail) {
            localDataSource.insertFavoriteCocktail(favoriteCocktail)
        }
    }
