package com.example.pocemons.ui.funs

object formatName {

    fun formatStatName(statName: String): String {
        return when (statName) {
            "hp" -> "HP"
            "attack" -> "Attack"
            "defense" -> "Defense"
            "special-attack" -> "Sp. Atk"
            "special-defense" -> "Sp. Def"
            "speed" -> "Speed"
            else -> statName.replace("-", " ").split(" ")
                .joinToString(" ") { it.capitalize() }
        }
    }


    fun getStatAbbreviation(statName: String): String {
        return when (statName) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> statName.take(3).uppercase()
        }
    }
}