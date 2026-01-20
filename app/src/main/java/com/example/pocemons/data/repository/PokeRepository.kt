package com.example.pocemons.data.repository

import android.util.Log
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.material.darkColors
import androidx.room.util.query
import com.example.pocemons.data.api.PokeApi
import com.example.pocemons.data.db.PokeDao
import com.example.pocemons.data.mapper.Mapper.toEntity
import com.example.pocemons.data.mapper.Mapper.toPokemonEntity
import com.example.pocemons.data.models.entity.PokemonEntity
import kotlin.collections.forEach

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

    suspend fun searchPokemon(query: String, ): List<PokemonEntity> {

       return try {
            Log.d("PokeRepository", "No local results, loading more from API...")
            val response = api.searchPokemons(500, 0)
            Log.d("PokeRepository", "API returned: ${response.results.size} pokemons")

            val apiResults = response.results.filter {
                it.name.contains(query, true)
            }

            val entity = apiResults.map {
                it.toPokemonEntity()
            }

            val existingNames = pokeDao.getPokemonNames()
            entity.forEach { pokemon ->
                if (!existingNames.contains(pokemon.name)) {
                    pokeDao.insertPoke(pokemon)
                }
            }

            Log.d("PokeRepository", "Search completed, returning ${entity.size} results")
            entity
        }
        catch (e: Exception) {
            Log.d("TAG", "searchPokemon: $e")
            return emptyList()
        }
    }
}