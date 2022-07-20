package com.example.androidfinalproject.ui.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.data.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor( val cocktailRepository: CocktailRepository) : ViewModel(){
    val margaritas = cocktailRepository.getCocktailsByName("%margarita%")
    val mojitos = cocktailRepository.getCocktailsByName("%mojito%")
    val pina = cocktailRepository.getCocktailsByName("%pina%")
    val mCocktails = cocktailRepository.getCocktailsByName("%m%")
    val jCocktails = cocktailRepository.getCocktailsByName("%j%")
    val allCocktails = cocktailRepository.getCocktails()
    val randomCocktail = cocktailRepository.getRandomCocktails()
    val randomRecommendedCocktails = cocktailRepository.getRandomCocktails()
    val summerCocktails = cocktailRepository.getRandomCocktails()

    fun updateCocktail(cocktail: Cocktail) = viewModelScope.launch(Dispatchers.IO) {
        cocktailRepository.updateCocktail(cocktail)
    }
}