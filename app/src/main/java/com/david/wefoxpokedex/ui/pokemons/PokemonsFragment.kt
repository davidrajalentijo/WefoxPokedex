package com.david.wefoxpokedex.ui.pokemons

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.david.wefoxpokedex.MainViewModel
import com.david.wefoxpokedex.OnActionsClickListener
import com.david.wefoxpokedex.R
import com.david.wefoxpokedex.ViewModelFactory
import com.david.wefoxpokedex.models.Pokemon
import com.david.wefoxpokedex.ui.detail.PokemonDetailActivity
import kotlinx.android.synthetic.main.initial_fragment.*

/*
    Fragment where appears the backpack and the use can see the list of pokemons captured
 */
class PokemonsFragment : Fragment(), OnActionsClickListener<Pokemon> {

    private lateinit var viewModel: MainViewModel
    private lateinit var lv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.pokemons_fragment, container, false)
        lv = rootView.findViewById(R.id.recyclerview_main_data_pokemons) as RecyclerView

        lv.layoutManager = GridLayoutManager(activity!!.applicationContext, 4)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)

        return rootView
    }

    override fun onPokemonClicked(view: View, pokemon: Pokemon) {
        startActivity(Intent(activity, PokemonDetailActivity::class.java).putExtra("pokemonId", pokemon.id))
    }

    fun getPokedex() {
        viewModel.loadPokemons().observe(this, Observer {
            lv.adapter = PokemonsAdapter(it!!.sortedBy { it.order }, this)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backpack_pokemon.setOnClickListener {
            initial_fragment.visibility = View.GONE
            getPokedex()
        }
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): PokemonsFragment {
            val fragment = PokemonsFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }

    }

}

