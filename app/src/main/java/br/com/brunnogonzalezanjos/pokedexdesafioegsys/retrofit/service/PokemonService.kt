package br.com.brunnogonzalezanjos.pokedexdesafioegsys.retrofit.service

import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonApiResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonsApiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    fun getAll(@Query("limit") limit: Int = 151): Call<PokemonsApiResult>

    @GET("pokemon/{id}")
    fun getPokemon(@Query("id") id: Int): Call<PokemonApiResult>
}