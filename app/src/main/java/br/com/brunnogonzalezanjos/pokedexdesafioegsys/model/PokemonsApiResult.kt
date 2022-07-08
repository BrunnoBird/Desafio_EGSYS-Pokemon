package br.com.brunnogonzalezanjos.pokedexdesafioegsys.model

data class PokemonsApiResult(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String,
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)
