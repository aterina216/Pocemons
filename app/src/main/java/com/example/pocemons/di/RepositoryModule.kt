package com.example.pocemons.di

import com.example.pocemons.data.api.PokeApi
import com.example.pocemons.data.db.PokeDao
import com.example.pocemons.data.repository.PokeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(dao: PokeDao, api: PokeApi): PokeRepository {
        return PokeRepository(dao, api)
    }
}