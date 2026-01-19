package com.example.pocemons.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.Pokemon

@Database(entities = [PokemonEntity::class], version = 2)
abstract class PokeDatabase: RoomDatabase() {

   abstract fun getPokeDao(): PokeDao
}