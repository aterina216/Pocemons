package com.example.pocemons.di

import com.example.pocemons.data.api.BaseUrl.BASE_URL
import com.example.pocemons.data.api.PokeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providePokeApi(retrofit: Retrofit) = retrofit.create(PokeApi::class.java)
}