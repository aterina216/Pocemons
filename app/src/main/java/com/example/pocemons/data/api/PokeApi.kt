package com.example.pocemons.data.api

import com.example.pocemons.data.models.response.PokeResponse
import retrofit2.http.GET

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemons(): PokeResponse
}