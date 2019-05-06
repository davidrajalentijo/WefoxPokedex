package com.david.wefoxpokedex.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonType(@SerializedName("type") @Expose var type: Type)

class Type(@SerializedName("name") @Expose var type_name: String)