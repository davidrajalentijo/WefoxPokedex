package com.david.wefoxpokedex.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.david.wefoxpokedex.models.Pokemon

/*
    DAO to manage the different calls in the room database
 */
@Dao
abstract class PokemonDao {
    @Query("SELECT * FROM pokemon")
    abstract fun loadPokemons(): LiveData<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon Where id=:id")
    abstract fun getPokemonbyId(id: String): LiveData<Pokemon>

}