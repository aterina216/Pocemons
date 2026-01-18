package com.example.pocemons.data.mapper

import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.Pokemon

object Mapper {

    fun Pokemon.toPokemonEntity(): PokemonEntity {
        return PokemonEntity(name = this.name, url = this.url)
    }
}