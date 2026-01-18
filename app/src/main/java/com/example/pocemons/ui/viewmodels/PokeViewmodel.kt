package com.example.pocemons.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokeViewmodel(private val repository: PokeRepository): ViewModel() {

    private var _pokemons = MutableStateFlow<List<PokemonEntity?>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity?>> = _pokemons

    init {
        Log.d("ViewModel", "PokeViewmodel: init")
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _pokemons.value = repository.getPokemons()
            Log.d("TAG", "loadPokemons: ${pokemons.value}")
        }
    }
}