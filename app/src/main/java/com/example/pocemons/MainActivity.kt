package com.example.pocemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pocemons.ui.HomeScreen
import com.example.pocemons.ui.theme.PocemonsTheme
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
            HomeScreen(viewModel)
        }
    }
}
