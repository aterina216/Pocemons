package com.example.pocemons.data.models.response

data class PokeResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)