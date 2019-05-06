package com.david.wefoxpokedex.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.david.wefoxpokedex.models.Pokemon
import com.david.wefoxpokedex.converters.SpritesConverter
import com.david.wefoxpokedex.converters.TypesConverter

/*
     Room database creation
 */
@Database(entities = [(Pokemon::class)], version = 1, exportSchema = false)
@TypeConverters(SpritesConverter::class, TypesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DATABASE_NAME: String = "PokemonsList"

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = android.arch.persistence.room.Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME)
                            .build()
                }
            }
            return instance as AppDatabase
        }
    }

    abstract fun pokemonDao(): PokemonDao

}