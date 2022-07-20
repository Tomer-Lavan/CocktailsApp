package com.example.androidfinalproject.ui.description_page

import androidx.lifecycle.*
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.data.repository.CocktailRepository
import com.example.androidfinalproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescriptionCocktailViewModel @Inject constructor(
    val cocktailRepository: CocktailRepository): ViewModel() {

    private val mutableIsFavorite= MutableLiveData<Int>()
    val isFavorite: LiveData<Int> get() = mutableIsFavorite

    fun setIsFavorite(value: Int) {
        mutableIsFavorite.value = value
    }

    private val _id =  MutableLiveData<Int>()

    fun updateCocktail(cocktail: Cocktail) = viewModelScope.launch(Dispatchers.IO) {
        cocktailRepository.updateCocktail(cocktail)
    }


    private val mutableSelectedCocktail = MutableLiveData<Cocktail>()
    val selectedCocktail: LiveData<Cocktail> get() = mutableSelectedCocktail

    fun selectCocktail(cocktail: Cocktail) {
        mutableSelectedCocktail.value = cocktail
    }
}