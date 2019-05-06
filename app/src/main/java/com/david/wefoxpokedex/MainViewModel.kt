package com.david.wefoxpokedex

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.david.wefoxpokedex.data.PokemonRepo
import com.david.wefoxpokedex.models.Pokemon

/*
    ViewModel to manage the call between the view and the repository
 */
class MainViewModel(private val pokemonRepository: PokemonRepo) : ViewModel() {

    fun getPokemon(): LiveData<Pokemon> {
        return pokemonRepository.getPokemon()
    }

    fun catchPokemon(pokemon: Pokemon) {
        pokemonRepository.catchPokemon(pokemon)
    }

    fun loadPokemons(): LiveData<List<Pokemon>> {
        return pokemonRepository.loadPokemons()
    }

    fun getPokemonbyId(id: String): LiveData<Pokemon> {
        return pokemonRepository.getPokemonbyId(id)
    }

}