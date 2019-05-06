package com.david.wefoxpokedex

import android.view.View
import com.david.wefoxpokedex.models.Pokemon

//Interface to manage different actions in the RecyclerView
interface OnActionsClickListener<T : Pokemon> {

    /**
     * The pokemon has been clicked
     */
    fun onPokemonClicked(view: View, item: T)

}