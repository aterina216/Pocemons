package com.example.pocemons.di

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.pocemons.data.db.PokeDao
import com.example.pocemons.data.db.PokeDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.jvm.java

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun getDataBase(context: Context): PokeDatabase {
        return Room.databaseBuilder(
            context,
            PokeDatabase::class.java,
            "poke_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun getDao(pokeDatabase: PokeDatabase): PokeDao = pokeDatabase.getPokeDao()
}