package com.example.pocemons

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.pocemons.ui.navigation.InitNavigation
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import com.example.pocemons.ui.viewmodels.PokeViewmodelFactory
import javax.inject.Inject

class MainActivity: ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: PokeViewmodelFactory

    private val viewModel: PokeViewmodel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as PokeApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InitNavigation(viewModel)
        }
    }
}
