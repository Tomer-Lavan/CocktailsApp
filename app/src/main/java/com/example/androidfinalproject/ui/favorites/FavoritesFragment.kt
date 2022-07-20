package com.example.androidfinalproject.ui.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidfinalproject.R
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.databinding.FragmentCocktailsSearchBinding
import com.example.androidfinalproject.databinding.FragmentFavoritesBinding
import com.example.androidfinalproject.ui.cocktails_search.CocktailsAdapter
import com.example.androidfinalproject.ui.cocktails_search.CocktailsSearchViewModel
import com.example.androidfinalproject.ui.description_page.DescriptionCocktailViewModel
import com.example.androidfinalproject.utils.Loading
import com.example.androidfinalproject.utils.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavoritesAdapter.CocktailItemListener  {

    private val viewModel : FavoritesViewModel by viewModels()

    private val descriptionCocktailViewModel: DescriptionCocktailViewModel by activityViewModels()

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    private  lateinit var  adapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoritesAdapter(this)
        binding.favoritesRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoritesRV.adapter = adapter

        viewModel.favoriteCocktails.observe(viewLifecycleOwner) {
            Log.i("cocktails changed", "start")
            when (it.status) {
                is Loading -> {
                    Log.i("cocktails changed", "Loading")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Success -> {
                    Log.i("cocktails changed", "Success")
                    binding.progressBar.visibility = View.GONE
                    adapter.setCocktails(it.status.data!!)
                }

                is Error -> {
                    Log.i("cocktails changed", "Error")
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.status.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onCocktailClick(cocktail: Cocktail) {
        findNavController().navigate(R.id.action_favoritesFragment_to_descriptionFragment)
        descriptionCocktailViewModel.selectCocktail(cocktail)
    }

    override fun onFavoriteClick(cocktail: Cocktail) {
        if (cocktail.isFavoriteCocktail == 1) {
            cocktail.isFavoriteCocktail = 0
        }
        adapter.removeCocktail(cocktail)
        viewModel.updateCocktail(cocktail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}