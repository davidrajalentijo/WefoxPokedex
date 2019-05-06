package com.david.wefoxpokedex.ui.pokemons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.david.wefoxpokedex.OnActionsClickListener
import com.david.wefoxpokedex.R
import com.david.wefoxpokedex.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_pokemons.view.*

/*
    Adapter to manage the list of pokemons
 */
class PokemonsAdapter(val pokemons: List<Pokemon>, private val actionsListener: OnActionsClickListener<Pokemon>) : RecyclerView.Adapter<PokemonsAdapter.PokemonsAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_pokemons, parent, false)
        return PokemonsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonsAdapterViewHolder, position: Int) {
        holder.bindPokemon(position)
    }

    inner class PokemonsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPokemon(position: Int) {
            val pokemon = pokemons[position]
            itemView.apply {
                pokemon_name.text = pokemon.name
                Picasso.get().load(pokemon.sprites.front_image).into(image_pokemon)

            }
            itemView.setOnClickListener {
                actionsListener.onPokemonClicked(itemView, pokemon)
            }
        }
    }

}