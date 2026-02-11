package com.example.pocemons.data.repository

import android.util.Log
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.room.util.query
import com.example.pocemons.data.api.PokeApi
import com.example.pocemons.data.db.PokeDao
import com.example.pocemons.data.mapper.Mapper.toEntity
import com.example.pocemons.data.mapper.Mapper.toPokemonEntity
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.PokemonDetailResponse
import java.sql.Time
import kotlin.collections.forEach

class PokeRepository(val pokeDao: PokeDao, val api: PokeApi) {

    private var currentOffset = 0
    private val pageSize = 20
    private var hasMorePokemons = true

    suspend fun getPokemons(loadMore: Boolean = false): List<PokemonEntity> {

        try {
            if(loadMore && !hasMorePokemons) {
                return emptyList()
            }

            if(!loadMore) {
                currentOffset = 0
                hasMorePokemons = true
            }

            val response = api.getPokemons(pageSize,
                offset = currentOffset)

            hasMorePokemons = response.next!= null

            currentOffset += pageSize

            val listName = pokeDao.getPokemonNames()
            val entity = response.results.map {
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
            val response = api.searchPokemons(1025, 0)
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

    suspend fun getPokemonByName(name: String): PokemonDetailResponse? {
        try {
            val pokemon = api.getPokemonByName(name)
            return pokemon
        }
        catch (e: java.lang.Exception) {
            return null
        }
    }

    suspend fun togglePokemonInTeam(id: Int, inTeam: Boolean){
        try {
            pokeDao.insertPokemonInTeam(id, inTeam)
        }
        catch (e: Exception) {
            Log.d("TAG", "togglePokemonInTeam: $e")
        }
    }

    suspend fun getTeamPokemons(): List<PokemonEntity>{
        return pokeDao.getTeamPokemons()
    }

    suspend fun updateViewAt(id: Int, time: Long) {
        try {
            pokeDao.updateViewAt(id, time)
        }
        catch (e: Exception) {
            Log.e("TAG", "updateViewAt: $e")
        }
    }

    suspend fun getHistoryPokemons(): List<PokemonEntity> {
        try {
            val list = pokeDao.getHistoryPokemons()
            return list
        }
        catch (e: Exception) {
            return emptyList()
        }
    }

    suspend fun clearOldHistory() {
        try {
            pokeDao.clearOldestFromHistory()
        }
        catch (e: Exception) {
            Log.e("TAG", "clearOldHistory: $e")
        }
    }

    suspend fun clearHistory() {
        try {
            pokeDao.clearHistory()
        }
        catch (e: Exception) {
            Log.e("TAG", "clearHistory: $e")
        }
    }
}