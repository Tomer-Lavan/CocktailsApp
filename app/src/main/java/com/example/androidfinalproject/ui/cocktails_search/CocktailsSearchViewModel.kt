package com.example.androidfinalproject.ui.cocktails_search

import android.util.Log
import androidx.lifecycle.*
import com.example.androidfinalproject.data.models.AllCocktails
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.data.models.FavoriteCocktail
import com.example.androidfinalproject.data.repository.CocktailRepository
import com.example.androidfinalproject.data.repository.FavoriteCocktailRepository
import com.example.androidfinalproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailsSearchViewModel @Inject constructor(
    val cocktailRepository: CocktailRepository, favoriteCocktailRepository: FavoriteCocktailRepository) : ViewModel() {

    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    private var _name = MutableLiveData<String>().default("")

    private var _id = MutableLiveData<Int>()

    fun updateCocktail(cocktail: Cocktail) = viewModelScope.launch(Dispatchers.IO) {
            cocktailRepository.updateCocktail(cocktail)
        }

    private val _cocktails = _name.switchMap {
        Log.i("start Search and Saving", "searching: %$it%")
        cocktailRepository.getCocktailsByName("%$it%", true)
    }



    val cocktails: LiveData<Resource<List<Cocktail>>> = _cocktails

    fun setName(name: String) {
        _name.value = name
    }
}
