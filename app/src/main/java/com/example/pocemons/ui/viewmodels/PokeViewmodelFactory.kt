package com.example.pocemons.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pocemons.data.repository.PokeRepository

class PokeViewmodelFactory(private val repository: PokeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokeViewmodel(repository) as T
    }
}