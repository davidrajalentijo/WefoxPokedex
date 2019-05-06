package com.david.wefoxpokedex.ui.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.david.wefoxpokedex.MainViewModel
import com.david.wefoxpokedex.R
import com.david.wefoxpokedex.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_not_found.*
import kotlinx.android.synthetic.main.search_pokemon.*
import java.text.SimpleDateFormat
import java.util.Date

/*
    Fragment to Search a Pokemon and have the option to capture or to leave
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.search_pokemon, container, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)

        viewModel.getPokemon().observe(this, Observer {
            if (it == null) {
                PokemonNotFoundContainer.visibility = View.VISIBLE
                pokemon_group.visibility = View.GONE
                catch_pokemon.visibility = View.GONE

            } else {
                pokemon_group.visibility = View.VISIBLE
                PokemonNotFoundContainer.visibility = View.GONE

                viewModel.getPokemonbyId(it.id).observe(this, Observer {
                    if (it == null) {
                        catch_pokemon.text = resources.getString(R.string.capture_text)
                    } else {
                        catch_pokemon.visibility = View.GONE
                        run.text = resources.getString(R.string.pokemon_in_pokedex_text)
                    }
                })

                var pokemon = it

                catch_pokemon.setOnClickListener {
                    val sdf = SimpleDateFormat("HH:mm:ss dd/M/yyyy")
                    val currentDate = sdf.format(Date()).toString()
                    pokemon.date = currentDate
                    viewModel.catchPokemon(pokemon)
                    Toast.makeText(activity!!.applicationContext, resources.getString(R.string.pokemon_captured), Toast.LENGTH_SHORT).show()
                }
                Picasso.get().load(it.sprites.front_image).into(image_pokemon)
                name_pokemon.text = it.name
                height_pokemon.text = it.height.toString()
                weight_pokemon.text = it.weight.toString()
            }
        })

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        run.setOnClickListener {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
        error_button.setOnClickListener {
            fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
        }
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

}