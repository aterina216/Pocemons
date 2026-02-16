package com.example.pocemons.ui.viewmodels

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pocemons.PokeApp
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.data.models.response.PokemonDetailResponse
import com.example.pocemons.data.repository.PokeRepository
import com.example.pocemons.utils.Prefs
import com.example.pocemons.utils.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.sql.Time

class PokeViewmodel(private val repository: PokeRepository, private val prefs: Prefs) : ViewModel() {

    private var _pokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity>> = _pokemons

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

    private var _hasMorePokemons = MutableStateFlow(true)
    val hasMorePokemons: StateFlow<Boolean> = _hasMorePokemons

    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _searchResults = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val searchResults: StateFlow<List<PokemonEntity>> = _searchResults

    private val _isSearching = MutableStateFlow(false)
    var isSearching: StateFlow<Boolean> = _isSearching


    var currentPokemon = MutableStateFlow<PokemonDetailResponse?>(null)

    private var _isLoadingPokemon = MutableStateFlow(false)
    val isLoadingPokemon: StateFlow<Boolean> = _isLoadingPokemon

    private val _teamPokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    var teamPokemons: StateFlow<List<PokemonEntity>> = _teamPokemons

    private var _historyPokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val historyPokemons: StateFlow<List<PokemonEntity>> = _historyPokemons

    private var _themeMode = MutableStateFlow(
        ThemeMode.fromInt(prefs.themeMode)
    )

    val themeMode: StateFlow<ThemeMode> = _themeMode

    private var _addInHistory = MutableStateFlow(prefs.addInhistory)
    val addInHistory: StateFlow<Boolean> = _addInHistory

    private var _startActivity = MutableStateFlow(prefs.startActivity)
    val startActivity: StateFlow<String> = _startActivity

    init {
        Log.d("ViewModel", "PokeViewmodel: init")
        loadPokemons()
        getTeamPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getPokemons(loadMore = false)
                _pokemons.value = result
                Log.d("TAG", "loadPokemons: ${pokemons.value}")
                _hasMorePokemons.value = true
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchPokemons(query: String) {
        viewModelScope.launch {
            if (query.length < 2) {
                _searchResults.value = emptyList()
                return@launch
            }

            _isSearching.value = true
            _error.value = null

            try {
                val results = repository.searchPokemon(query)
                if (results != null) {
                    Log.d("TAG", "searchPokemons: $results")
                    _searchResults.value = results
                }
            } catch (e: Exception) {
                Log.d("TAG", "searchPokemons: ${e.message}")
                _error.value = e.message
            } finally {
                _isSearching.value = false
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
        _isSearching.value = false
    }

    fun loadMorePokemons() {
        viewModelScope.launch {
            if (_isLoading.value || !_hasMorePokemons.value) {
                return@launch
            }

            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getPokemons(true)

                if (result.isNotEmpty()) {
                    val currentList = _pokemons.value.toMutableList()
                    currentList.addAll(result)
                    _pokemons.value = currentList
                    Log.d(
                        "PokeViewmodel",
                        "Loaded more: ${result.size} pokemons, total: ${currentList.size}"
                    )
                } else {
                    _hasMorePokemons.value = false
                    Log.d("PokeViewmodel", "No more pokemons to load")
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("PokeViewmodel", "Error loading more pokemons: ${e.message}")
            } finally {
                _isLoadingMore.value = false
                _isLoading.value = false
            }
        }
    }

    fun getPokemonDetail(name: String) {

        viewModelScope.launch {

            currentPokemon.value = null
            _isLoadingPokemon.value = true

            try {
                currentPokemon.value = repository.getPokemonByName(name)
                _isLoadingPokemon.value = false
            } catch (e: Exception) {
                Log.e("PokeViewmodel", "Error getting pokemon detail: ${e.message}")
            } finally {
                _isLoadingPokemon.value = false
            }
        }
    }

    fun togglePokemonInTeam(id: Int) {
        viewModelScope.launch {
            val currentList = _teamPokemons.value.toMutableList()
            val isInTeam = currentList.any { it.id == id }

            if (isInTeam) {
                currentList.removeAll { it.id == id }
                repository.togglePokemonInTeam(id, false)
            } else {
                val pokemonToAdd = _pokemons.value.find { it.id == id }
                    ?: _searchResults.value.find { it.id == id }

                if (pokemonToAdd != null) {

                    val updatedPokemon = pokemonToAdd.copy(inTeam = true)
                    currentList.add(updatedPokemon)

                    repository.togglePokemonInTeam(id, true)
                }
            }
            _teamPokemons.value = currentList
        }
    }

    private fun updateMainListTeamStatus(id: Int, inTeam: Boolean) {
        val updatedMainList = _pokemons.value.map { pokemon ->
            if(pokemon.id == id) pokemon.copy(inTeam = inTeam) else pokemon
        }
        _pokemons.value = updatedMainList

        val updatedSearchResults = _searchResults.value.map { pokemon ->
            if(pokemon.id == id) pokemon.copy(inTeam = inTeam) else pokemon
        }
        _searchResults.value = updatedSearchResults
    }

    fun getTeamPokemons() {
        viewModelScope.launch {
            try {
                _teamPokemons.value = repository.getTeamPokemons()
            }
            catch (e: Exception) {
                Log.e("PokeViewmodel", "Error getting team pokemons: ${e.message}")
            }
        }
    }

    fun updateViewAt(id: Int, time: Long) {
        viewModelScope.launch {
            try {
                if(_addInHistory.value) {
                    repository.updateViewAt(id, time)
                }
            }
            catch (e: Exception) {
                Log.e("PokeViewmodel", "Error updating view at: ${e.message}")
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            try {
                repository.clearOldHistory()
                _historyPokemons.value = repository.getHistoryPokemons()
            }
            catch (e: Exception){
                Log.e("PokeViewmodel", "Error loading history: ${e.message}")
            }
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            try {
                repository.clearHistory()
                _historyPokemons.value = emptyList()
            }
            catch (e: Exception){
                Log.e("PokeViewmodel", "Error clearing history: ${e.message}")
            }
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        Log.d("PokeViewmodel", "setThemeMode: $mode, previous: ${_themeMode.value}")
        prefs.themeMode = mode.value
        _themeMode.value = mode
    }

    fun setAddInHistory(addInHistory: Boolean) {
        prefs.addInhistory = addInHistory
        _addInHistory.value = addInHistory
    }

    fun clearAll() {
        viewModelScope.launch {
            try {
                repository.clearAll()
                loadPokemons()
                _teamPokemons.value = emptyList()
            }
            catch (e: Exception){
                Log.e("PokeViewmodel", "Error clearing all: ${e.message}")
            }
        }
    }

    fun setStartActivity(startActivity: String) {
        prefs.startActivity = startActivity
        _startActivity.value = startActivity
    }
}
