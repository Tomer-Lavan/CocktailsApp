package com.example.androidfinalproject.data.loacal_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.data.models.FavoriteCocktail

@Dao
interface FavoriteCocktailDao {

    @Query("SELECT * FROM favorites")
    fun  getAllFavoriteCocktails() : LiveData<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCocktail(favoriteCocktail: FavoriteCocktail)
}