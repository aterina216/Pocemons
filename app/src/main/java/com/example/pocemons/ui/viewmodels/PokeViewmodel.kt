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

    private var _pokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity>> = _pokemons

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _searchResults = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val searchResults: StateFlow<List<PokemonEntity>> = _searchResults

    private val _isSearching = MutableStateFlow(false)
    var isSearching: StateFlow<Boolean> = _isSearching

    init {
        Log.d("ViewModel", "PokeViewmodel: init")
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _isLoading.value = true
            try{
                _pokemons.value = repository.getPokemons()
                Log.d("TAG", "loadPokemons: ${pokemons.value}")
            }
            catch (e: Exception){
                _error.value = e.message
            }

            finally {
                _isLoading.value = false
            }
        }
    }

    fun searchPokemons(query: String) {
        viewModelScope.launch {
            if(query.length<2) {
                _searchResults.value = emptyList()
                return@launch
            }

            _isSearching.value = true
            _error.value = null

            try {
                val results = repository.searchPokemon(query)
                if(results != null) {
                    Log.d("TAG", "searchPokemons: $results")
                    _searchResults.value = results
                }
            }
            catch (e: Exception) {
                Log.d("TAG", "searchPokemons: ${e.message}")
                _error.value = e.message
            }

            finally {
                _isSearching.value = false
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
        _isSearching.value = false
    }
}