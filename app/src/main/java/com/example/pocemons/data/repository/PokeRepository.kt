package com.example.pocemons.data.repository

import androidx.compose.foundation.interaction.HoverInteraction
import com.example.pocemons.data.api.PokeApi
import com.example.pocemons.data.db.PokeDao
import com.example.pocemons.data.mapper.Mapper.toPokemonEntity
import com.example.pocemons.data.models.entity.PokemonEntity

class PokeRepository(val pokeDao: PokeDao, val api: PokeApi) {

    suspend fun getPokemons(): List<PokemonEntity> {

        try {
            val response = api.getPokemons().results

            val listName = pokeDao.getPokemonNames()
            val entity = response.map {
                it.toPokemonEntity()
            }
            entity.forEach { if (!listName.contains(it.name)) pokeDao.insertPoke(it) }
            return entity
        }
        catch (e: Exception) {
            return pokeDao.getPokemons()
        }
    }
}