package com.example.pocemons

import com.example.pocemons.data.mapper.Mapper.extractId
import com.example.pocemons.data.mapper.Mapper.getImageUrl
import com.example.pocemons.data.mapper.Mapper.toEntity
import com.example.pocemons.data.mapper.Mapper.toPokemonEntity
import com.example.pocemons.data.models.response.OfficialArtwork
import com.example.pocemons.data.models.response.OtherSprites
import com.example.pocemons.data.models.response.Pokemon
import com.example.pocemons.data.models.response.PokemonDetailResponse
import com.example.pocemons.data.models.response.Sprites
import com.example.pocemons.data.models.response.Stat
import com.example.pocemons.data.models.response.StatInfo
import com.example.pocemons.data.models.response.Type
import com.example.pocemons.data.models.response.TypeSlot
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MapperTest {

    @Test
    fun `toPokemonEntity correctly maps Pokemon`() {
        val pokemon = Pokemon(
            name = "bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )

        val entity = pokemon.toPokemonEntity()

        assertEquals(1, entity.id)
        assertEquals("bulbasaur", entity.name)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
            entity.imageUrl
        )
        assertEquals("https://pokeapi.co/api/v2/pokemon/1/", entity.url)
    }

    @Test
    fun `toPokemonEntity handles invalid URL gracefully`() {
        val pokemon = Pokemon(
            name = "unknown",
            url = "not-a-valid-url"
        )

        val entity = pokemon.toPokemonEntity()
        assertEquals(0, entity.id)
        assertEquals("unknown", entity.name)
        assertTrue(entity.imageUrl.contains("/0.png"))
    }

    @Test
    fun `extractId extracts id from URL`(){
        val pokemon = Pokemon(
            name = "charmander",
            url = "https://pokeapi.co/api/v2/pokemon/4/"
        )

        val id = pokemon.extractId()
        assertEquals(4, id)
    }

    @Test
    fun `extractId returns 0 for invalid URL`(){
        val pokemon = Pokemon(
            name = "broken",
            url = "invalid-url"
        )

        val id = pokemon.extractId()
        assertEquals(0, id)
    }

    @Test
    fun `getImageUrl returns correct URL`(){
        val pokemon = Pokemon(
            name = "squirtle",
            url = "https://pokeapi.co/api/v2/pokemon/7/"
        )

        val imageUrl = pokemon.getImageUrl()
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
            imageUrl
        )
    }

    @Test
    fun `PokemonDetailResponse toEntity correctly maps PokemonDetailResponse`() {
        val detail = PokemonDetailResponse(
            id = 25,
            name = "pikachu",
            sprites = Sprites(
                front_default = "front.png",
                other = OtherSprites(
                    officialArtwork = OfficialArtwork(
                        front_default = "official-artwork.png"
                    )
                )
            ),
            types = listOf(
                TypeSlot(slot = 1, type = Type(name = "electric"))
            ),
            height = 4,
            weight = 60,
            stats = listOf(
                Stat(base_stat = 35, stat = StatInfo(name = "hp"))
            )
        )

        val entity = detail.toEntity()
        assertEquals(25, entity.id)
        assertEquals("pikachu", entity.name)
        assertEquals("official-artwork.png", entity.imageUrl)
        assertEquals("https://pokeapi.co/api/v2/pokemon/25/", entity.url)
    }

    @Test
    fun `PokemonDetailResponse toEntity falls back to default artwork when official artwork missing`(){
        val detail = PokemonDetailResponse(
            id = 25,
            name = "pikachu",
            sprites = Sprites(
                front_default = "front.png",
                other = OtherSprites(
                    officialArtwork = null // отсутствует
                )
            ),
            types = listOf(
                TypeSlot(slot = 1, type = Type(name = "electric"))
            ),
            height = 4,
            weight = 60,
            stats = listOf(
                Stat(base_stat = 35, stat = StatInfo(name = "hp"))
            )
        )

        val entity = detail.toEntity()

        assertEquals(25, entity.id)
        assertEquals("pikachu", entity.name)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
            entity.imageUrl
        )
    }

    @Test
    fun `PokemonDetailResponse toEntity - falls back to default when other sprites completely missing`(){
        val detail = PokemonDetailResponse(
            id = 25,
            name = "pikachu",
            sprites = Sprites(
                front_default = "front.png",
                other = null // вообще нет other-спрайтов
            ),
            types = listOf(
                TypeSlot(slot = 1, type = Type(name = "electric"))
            ),
            height = 4,
            weight = 60,
            stats = listOf(
                Stat(base_stat = 35, stat = StatInfo(name = "hp"))
            )
        )

        val entity = detail.toEntity()
        assertEquals(25, entity.id)
        assertEquals("pikachu", entity.name)
        assertEquals(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
            entity.imageUrl
        )
    }
}