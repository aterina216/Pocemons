package com.example.pocemons.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity (
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val imageUrl: String,
    val url: String,
    var inTeam: Boolean = false
)
{
    fun capitalizedName(): String {
        return name.replaceFirstChar { it.uppercase() }
    }

    fun getPokemonNumber(): String {
        return "#${id.toString().padStart(3, '0')}"
    }
}