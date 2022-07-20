package com.example.androidfinalproject.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCocktail(
    @PrimaryKey
    val idDrink	: Int
)
