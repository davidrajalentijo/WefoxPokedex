package com.david.wefoxpokedex.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.david.wefoxpokedex.MainViewModel
import com.david.wefoxpokedex.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_pokemon.*
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.david.wefoxpokedex.R

/*
    Activity that shows one pokemon in detail
 */
class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pokemon)

        val lv = findViewById<RecyclerView>(R.id.recyclerview_type_pokemons)
        lv.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.HORIZONTAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MainViewModel::class.java)

        val id = intent.extras.getString("pokemonId")

        viewModel.getPokemonbyId(id).observe(this, Observer {
            if (it != null) {
                val type = it.types[0].type.type_name.replace("[", "").replace("]", "").split(",")
                lv.adapter = TypeAdapter(type)

                name_pokemon.text = it.name
                Picasso.get().load(it.sprites.front_image).into(image_pokemon)
                height_pokemon.text = it.height.toString()
                weight_pokemon.text = it.weight.toString()
                base_experience_pokemon.text = it.base_experience.toString()
                date_captured_pokemon.text = it.date
            }
        })

    }
}