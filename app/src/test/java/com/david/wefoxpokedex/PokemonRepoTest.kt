package com.david.wefoxpokedex

import com.david.wefoxpokedex.data.PokemonRepo
import com.david.wefoxpokedex.data.WebService
import com.david.wefoxpokedex.database.PokemonDao
import com.david.wefoxpokedex.models.Pokemon
import com.david.wefoxpokedex.models.PokemonType
import com.david.wefoxpokedex.models.Sprites
import com.david.wefoxpokedex.models.Type
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.Executor

class PokemonRepoTest {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var api: WebService

    private lateinit var pokemonRepository: PokemonRepo

    @Before
    fun setup() {
        pokemonDao = Mockito.mock(PokemonDao::class.java)
        api = Mockito.mock(WebService::class.java)

        val executor = Executor { command -> command.run() }

        pokemonRepository = PokemonRepo(api, executor, pokemonDao)

    }

    @Test
    fun `pokemonDao-getPokemonbyId is called`() {
        pokemonRepository.getPokemonbyId("1")
        Mockito.verify(pokemonDao).getPokemonbyId("1")
    }

    @Test
    fun `pokemonDao-loadPokemons is called`() {
        pokemonRepository.loadPokemons()
        Mockito.verify(pokemonDao).loadPokemons()
    }

    @Test
    fun `pokemonDao-catchPokemon is called`() {
        val pokemon = Pokemon("a", 1, 1, 1, 1, "1", Sprites("a"), listOf(PokemonType(Type("a"))), "a")
        pokemonRepository.catchPokemon(pokemon)
        Mockito.verify(pokemonDao).insertPokemon(pokemon)
    }



}