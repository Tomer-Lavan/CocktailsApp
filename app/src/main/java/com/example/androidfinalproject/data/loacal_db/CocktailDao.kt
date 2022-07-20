package com.example.androidfinalproject.data.loacal_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidfinalproject.data.models.AllCocktails
import com.example.androidfinalproject.data.models.Cocktail

@Dao
interface CocktailDao {
    
    @Query("SELECT * FROM cocktails")
    fun  getAllCocktails() : LiveData<List<Cocktail>>
    
    @Query("SELECT * FROM cocktails WHERE idDrink = :id")
    fun getCocktail(id : Int) : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails WHERE strDrink LIKE :name")
    fun getCocktailsByName(name : String) : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails WHERE isFavoriteCocktail = 1")
    fun getFavoritesCocktails() : LiveData<List<Cocktail>>

    @Query("SELECT * FROM cocktails ORDER BY random() LIMIT 10")
    fun getRandomCocktails() : LiveData<List<Cocktail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCocktail(cocktail: Cocktail)

    @Update
    fun updateCocktail(cocktail: Cocktail)

    @Update
    fun updateCocktails(cocktails : List<Cocktail>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktails(cocktails : List<Cocktail>)
}