package com.david.wefoxpokedex.converters

import android.arch.persistence.room.TypeConverter
import com.david.wefoxpokedex.models.Sprites

/*
      Convert Sprites to String and String to Sprites
 */
class SpritesConverter {

    @TypeConverter
    fun fromSprites(value: Sprites): String? {
        return value.front_image
    }

    @TypeConverter
    fun toSprites(value: String): Sprites {
        return Sprites(value)
    }
}