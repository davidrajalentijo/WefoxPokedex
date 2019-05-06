package com.david.wefoxpokedex.data

import com.david.wefoxpokedex.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/*
    Different calls to Pokeapi
 */
interface WebService {

    @GET
    fun getPokemon(@Url url: String): Call<Pokemon>

}