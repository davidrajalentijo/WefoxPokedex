package com.david.wefoxpokedex.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
class Pokemon(
        @ColumnInfo(name = "name")
        @SerializedName("name")
        @Expose
        var name: String,

        @ColumnInfo(name = "weight")
        @SerializedName("weight")
        @Expose
        var weight: Int,

        @ColumnInfo(name = "height")
        @SerializedName("height")
        @Expose
        var height: Int,

        @ColumnInfo(name = "base_experience")
        @SerializedName("base_experience")
        @Expose
        var base_experience: Int,

        @ColumnInfo(name = "order")
        @SerializedName("order")
        @Expose
        var order: Int,

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        @Expose
        var id: String,

        @ColumnInfo(name = "sprites")
        @SerializedName("sprites")
        @Expose
        var sprites: Sprites,

        @ColumnInfo(name = "types")
        @SerializedName("types")
        @Expose
        var types: List<PokemonType>,

        @ColumnInfo(name = "date")
        @Expose
        var date: String
)
