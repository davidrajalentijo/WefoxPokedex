package com.david.wefoxpokedex.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.david.wefoxpokedex.models.Pokemon
import com.david.wefoxpokedex.database.PokemonDao
import com.david.wefoxpokedex.ViewModelFactory
import java.io.IOException
import java.util.concurrent.Executor
import kotlin.random.Random

/**
 * Repository (repository pattern) to access pokemon content. Pokemons is maintained offline. PokemonRepo is responsible from providing data to the upper layer
 * so it's not aware of where that data is coming from, local db or network.
 */

class PokemonRepo(private val webservice: WebService, private val executor: Executor, private val pokemonDao: PokemonDao) {

    /*Get Pokemon from Retrofit*/
    fun getPokemon(): LiveData<Pokemon> {
        val data = MutableLiveData<Pokemon>()
        executor.execute {
            try {
                val response = webservice.getPokemon(ViewModelFactory.BASE_URL + Random.nextInt(MIN_RANDOM, MAX_RANDOM)).execute()
                if (response.isSuccessful && response.body() != null) {
                    data.postValue(response.body())

                } else {
                    //TODO return error to the upper layer somehow
                    data.postValue(null)
                }
            } catch (e: IOException) {
                data.postValue(null)
                //TODO return error to the upper layer somehow
            }
        }
        return data
    }

    /*Save the pokemon in the db*/
    fun catchPokemon(pokemon: Pokemon) {
        executor.execute {
            try {
                pokemonDao.insertPokemon(pokemon)
            } catch (e: IOException) {
                //TODO return error to the upper layer somehow
            }
        }
    }

    /*Get the pokemons in the db*/
    fun loadPokemons(): LiveData<List<Pokemon>> {
        return pokemonDao.loadPokemons()
    }

    /*Get one pokemon by id in the db*/
    fun getPokemonbyId(id: String): LiveData<Pokemon> {
        return pokemonDao.getPokemonbyId(id)
    }

    companion object {
        private const val MIN_RANDOM = 1
        private const val MAX_RANDOM = 1000
    }

}