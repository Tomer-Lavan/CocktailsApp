package com.example.androidfinalproject.data.remote_db

import com.example.androidfinalproject.data.models.AllCocktails
import com.example.androidfinalproject.data.models.Cocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailService {

    @GET("json/v1/1/filter.php?c=Cocktail")
    suspend fun getAllCocktails() : Response<AllCocktails>

    @GET("json/v1/1/lookup.php?")
    suspend fun getCocktail(@Query("i") id : Int) : Response<AllCocktails>

    @GET("json/v1/1/search.php?")
    suspend fun getAllCocktailsByName(@Query("s") name : String) : Response<AllCocktails>
}