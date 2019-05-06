package com.david.wefoxpokedex.converters

import android.arch.persistence.room.TypeConverter
import com.david.wefoxpokedex.models.PokemonType
import com.david.wefoxpokedex.models.Type
import com.google.gson.Gson

/*
      Convert Types to String and String to Types
 */
class TypesConverter {

    @TypeConverter
    fun fromTypes(value: List<PokemonType>): String {
        val mutableList = mutableListOf<String>()
        value.forEach {
            mutableList.add(it.type.type_name)
        }
        return Gson().toJson(mutableList)

    }

    @TypeConverter
    fun toTypes(value: String): List<PokemonType> {
        return listOf(PokemonType(Type(value)))
    }
}