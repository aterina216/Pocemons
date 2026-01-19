import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pocemons.data.models.entity.PokemonEntity
import com.example.pocemons.ui.components.PocemonCard
import com.example.pocemons.ui.components.PokeballBackground
import com.example.pocemons.ui.components.PokemonErrorScreen
import com.example.pocemons.ui.components.PokemonLoadingScreen
import com.example.pocemons.ui.theme.PokemonBlack
import com.example.pocemons.ui.theme.PokemonRed
import com.example.pocemons.ui.viewmodels.PokeViewmodel
import androidx.compose.runtime.setValue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: PokeViewmodel,  // ← исправил именование (viewmodel → viewModel)
    onPokemonClick: (PokemonEntity?) -> Unit = {}
) {
    val pokemons: List<PokemonEntity> by viewModel.pokemons.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    val error by viewModel.error.collectAsState()

    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    val lazyListState = rememberLazyListState()  // ← исправил опечатку (lazyListStater → lazyListState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PokemonRed.copy(alpha = 0.2f),
                        PokemonBlack
                    )
                )
            )
    ) {
        PokeballBackground()

        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokedex",
                        color = PokemonRed,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = PokemonBlack.copy(alpha = 0.9f)
                ),
                modifier = Modifier.background(Color.Transparent)
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = {
                    Text("Найти покемона...", color = Color.Gray)
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PokemonRed,
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = PokemonBlack.copy(alpha = 0.5f),
                    unfocusedContainerColor = PokemonBlack.copy(alpha = 0.5f),
                    cursorColor = PokemonRed
                ),
                singleLine = true
            )

            Text(
                text = "Всего покемонов: ${pokemons.size}",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Box(modifier = Modifier.fillMaxSize()) {
                when {
                    isLoading ->
                        PokemonLoadingScreen()

                    error != null -> {
                        PokemonErrorScreen(
                            message = error ?: "Неизвестная ошибка",
                            onRetry = { viewModel.loadPokemons() }
                        )
                    }

                    pokemons.isEmpty() -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Покемоны не найдены",
                                color = Color.Gray,
                                fontSize = 18.sp
                            )

                            Button(
                                onClick = { viewModel.loadPokemons() },
                                modifier = Modifier.padding(top = 16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PokemonRed
                                )
                            ) {
                                Text("Загрузить покемонов")
                            }
                        }
                    }

                    else -> {
                        val filteredPokemons = pokemons.filter { pokemon ->
                            searchText.text.isEmpty() ||
                                    pokemon.name.contains(searchText.text, ignoreCase = true) ||
                                    pokemon.id.toString().contains(searchText.text)
                        }

                        LazyColumn(
                            state = lazyListState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filteredPokemons) { pokemon: PokemonEntity ->  // ← Явно указываем тип
                                PocemonCard(  // ← Убедись, что название правильное
                                    pokemon = pokemon,
                                    onClick = { onPokemonClick(pokemon) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}