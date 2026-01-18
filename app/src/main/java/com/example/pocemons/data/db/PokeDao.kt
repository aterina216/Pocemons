package com.example.pocemons.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pocemons.data.models.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {

    @Insert
    suspend fun insertPoke(pokemon: PokemonEntity)

    @Insert
    suspend fun insertPokemons(pokemon: List<PokemonEntity>)

    @Delete
    suspend fun deletePoke(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemons")
    suspend fun getPokemons(): List<PokemonEntity>

    @Query("SELECT name FROM pokemons")
    suspend fun getPokemonNames(): List<String>
}