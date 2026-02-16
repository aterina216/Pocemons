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

    @Query("UPDATE pokemons SET inTeam = :inTeam WHERE id= :id")
    suspend fun insertPokemonInTeam(id: Int, inTeam: Boolean)

    @Query("SELECT * FROM pokemons WHERE inTeam = 1")
    suspend fun getTeamPokemons(): List<PokemonEntity>

    @Query("UPDATE pokemons SET viewAt = :viewAt WHERE id= :id")
    suspend fun updateViewAt(id: Int, viewAt: Long)

    @Query("SELECT * FROM pokemons WHERE viewAt IS NOT NULL ORDER BY viewAt DESC")
    suspend fun getHistoryPokemons(): List<PokemonEntity>

    @Query("""
        UPDATE pokemons 
        SET viewAt = NULL 
        WHERE id IN (
            SELECT id FROM pokemons 
            WHERE viewAt IS NOT NULL AND viewAt > 0 
            ORDER BY viewAt ASC 
            LIMIT (SELECT MAX(0, COUNT(*) - 100) FROM pokemons WHERE viewAt IS NOT NULL AND viewAt > 0)
        )
    """)
    suspend fun clearOldestFromHistory()

    @Query("UPDATE pokemons SET viewAt = NULL WHERE viewAt IS NOT  NULL AND viewAt > 0")
    suspend fun clearHistory()

    @Query("DELETE FROM pokemons")
    suspend fun clearAll()
}