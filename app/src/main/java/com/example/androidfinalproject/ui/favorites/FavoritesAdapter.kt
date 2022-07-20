package com.example.androidfinalproject.ui.favorites

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.databinding.ItemCocktailBinding
import com.example.androidfinalproject.ui.cocktails_search.CocktailsAdapter

class FavoritesAdapter(private val listener: CocktailItemListener) :
    RecyclerView.Adapter<FavoritesAdapter.CocktailViewHolder>() {

    private val favoritesCocktails = ArrayList<Cocktail>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailBinding,
                             private val listener: CocktailItemListener
    )
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: Cocktail

        init {
            itemBinding.root.setOnClickListener(this)
            itemBinding.favorite.setOnClickListener(this)
        }

        fun bind(item: Cocktail) {
            this.cocktail = item
            itemBinding.name.text = item.strDrink
            Glide.with(itemBinding.root)
                .load(item.strDrinkThumb)
                .into(itemBinding.image)
            itemBinding.favorite.isSelected = true


            itemBinding.favorite.setOnClickListener() {
                onFavoriteClick(item)
            }

        }

        override fun onClick(v: View?) {
            listener.onCocktailClick(cocktail)
        }

        fun onFavoriteClick(cocktail: Cocktail) {
            listener.onFavoriteClick(cocktail)
        }
    }

    fun setCocktails(cocktails : Collection<Cocktail>) {
        this.favoritesCocktails.clear()
        this.favoritesCocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    fun removeCocktail(cocktail: Cocktail) {
        this.favoritesCocktails.remove(cocktail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.CocktailViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CocktailViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) =
        holder.bind(favoritesCocktails[position])


    override fun getItemCount() = favoritesCocktails.size

    interface CocktailItemListener {
        fun onCocktailClick(cocktail : Cocktail)
        fun onFavoriteClick(cocktail: Cocktail)
    }
}

