package com.david.wefoxpokedex.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.david.wefoxpokedex.R
import kotlinx.android.synthetic.main.type_list.view.*

class TypeAdapter(val pokemons: List<String>) : RecyclerView.Adapter<TypeAdapter.TypeAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.type_list, parent, false)
        return TypeAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeAdapterViewHolder, position: Int) {
        holder.bindPokemon(position)
    }

    inner class TypeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPokemon(position: Int) {
            val pokemon = pokemons[position]
            itemView.apply {
                pokemon_type.text = pokemon
            }

        }
    }

}