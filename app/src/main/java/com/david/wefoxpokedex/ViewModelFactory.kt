package com.david.wefoxpokedex

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.david.wefoxpokedex.data.PokemonRepo
import com.david.wefoxpokedex.data.WebService
import com.david.wefoxpokedex.database.AppDatabase
import com.david.wefoxpokedex.database.PokemonDao
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/*
    ModelFactory to create retrofit Connection
 */
class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(createPokemonRepo(context)) as T
    }

    private fun createPokemonRepo(context: Context): PokemonRepo {

        return PokemonRepo(createWebService(), createExecutor(), createPokemonDao(context))
    }

    private fun createWebService(): WebService {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(WebService::class.java)
    }

    private fun createExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    private fun createPokemonDao(context: Context): PokemonDao {
        val d: AppDatabase = AppDatabase.getInstance(context)
        return d.pokemonDao()
    }

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

    }
}