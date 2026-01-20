package com.example.pocemons.data.mapper

import androidx.compose.ui.input.pointer.PointerIcon
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.Pokemon
import com.example.pocemons.data.models.response.PokemonDetailResponse

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

    fun PokemonDetailResponse.toEntity(): PokemonEntity {
        return PokemonEntity(
            id = id,
            name = name,
            imageUrl = this.sprites.other?.officialArtwork?.front_default
                ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${this.id}.png",
            url = "https://pokeapi.co/api/v2/pokemon/${this.id}/")
    }
}