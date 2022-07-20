package com.example.androidfinalproject.ui.main_page

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfinalproject.data.models.Cocktail
import com.example.androidfinalproject.databinding.ItemCocktailMainpageBinding

class MainPageAdapter(private val listener: CocktailItemListener) :
    RecyclerView.Adapter<MainPageAdapter.CocktailViewHolder>() {

    private val allCocktails = ArrayList<Cocktail>()

    class CocktailViewHolder(private val itemBinding: ItemCocktailMainpageBinding,
                             private val listener: CocktailItemListener
    )
        : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var cocktail: Cocktail

        init {
            itemBinding.root.setOnClickListener(this)
        }

        fun bind(item: Cocktail) {
            this.cocktail = item
            itemBinding.name.text = item.strDrink
            Glide.with(itemBinding.root)
                .load(item.strDrinkThumb)
                .into(itemBinding.image)

        }

        override fun onClick(v: View?) {
            listener.onCocktailClick(cocktail)
        }
    }

    fun setCocktails(cocktails : Collection<Cocktail>) {
        this.allCocktails.clear()
        this.allCocktails.addAll(cocktails)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CocktailViewHolder {
        val binding = ItemCocktailMainpageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CocktailViewHolder(binding, listener)
    }


    override fun getItemCount() = allCocktails.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bind(allCocktails[position])
    }

    interface CocktailItemListener {
        fun onCocktailClick(cocktail : Cocktail)
    }
}