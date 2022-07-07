package br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.Pokemon
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonsApiResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.retrofit.webclient.PokemonWebClient

class PokemonRepository(
    private val webClient: PokemonWebClient = PokemonWebClient()
) {
    private val pokemonsFound = MutableLiveData<Resource<PokemonsApiResult>?>()

    fun getAll(): LiveData<Resource<PokemonsApiResult>?> {
        val updatePokemonList: (PokemonsApiResult) -> Unit = {
            pokemonsFound.value = Resource(data = it);
        }
        searchInAPI(
            whenFail = { error ->
                Log.i("CALLAPI", "REPOSITORY FAIL: " + error)
                val actualResource = pokemonsFound.value
                val resourceFail = createResourceFail<PokemonsApiResult>(
                    actualResource,
                    error
                )
                pokemonsFound.value = resourceFail
            })
        return pokemonsFound
    }

    private fun searchInAPI(
        whenFail: (erro: String?) -> Unit
    ) {
        webClient.getAll(
            whenSuccess = { newPokemon ->
                newPokemon?.let { pokemonResult ->
                    Log.i("CALLAPI", "REPOSITORY SUCCESS")
                    pokemonsFound.value = Resource(pokemonResult)
                }
            }, whenFail = whenFail
        )
    }
}