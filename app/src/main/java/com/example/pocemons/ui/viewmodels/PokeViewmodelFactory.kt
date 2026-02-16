package com.example.pocemons.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pocemons.data.repository.PokeRepository
import com.example.pocemons.utils.Prefs

class PokeViewmodelFactory(private val repository: PokeRepository, private val prefs: Prefs): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokeViewmodel(repository, prefs) as T
    }
}