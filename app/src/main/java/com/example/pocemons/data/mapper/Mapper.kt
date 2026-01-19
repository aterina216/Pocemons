package com.example.pocemons.data.mapper

import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.Pokemon

object Mapper {

    fun Pokemon.toPokemonEntity(): PokemonEntity {
        val id = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0
        return PokemonEntity(
            id = id,
            name = this.name,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png",
            url = this.url)
    }

    fun Pokemon.extractId() : Int {
        return  url.trimEnd('/')
            .substringAfterLast('/')
            .toIntOrNull() ?: 0
    }

    fun Pokemon.getImageUrl(): String {
        val id = extractId()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}