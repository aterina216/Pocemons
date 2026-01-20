package com.example.pocemons.data.api


import com.example.pocemons.data.models.response.PokeResponse
import com.example.pocemons.data.models.response.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokeResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonDetailResponse

    @GET("pokemon")
    suspend fun searchPokemons(
        @Query("limit") limit: Int = 1025,  // Увеличиваем для поиска
        @Query("offset") offset: Int = 0
    ): PokeResponse
}