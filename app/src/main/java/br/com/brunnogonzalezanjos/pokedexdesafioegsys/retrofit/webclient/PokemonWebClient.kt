package br.com.brunnogonzalezanjos.pokedexdesafioegsys.retrofit.webclient

import android.util.Log
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.Pokemon
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonApiResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonsApiResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.retrofit.AppRetrofit
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.retrofit.service.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val UNSUCCESSFUL_REQUEST = "Unsuccessful request"

class PokemonWebClient(
    private val service: PokemonService = AppRetrofit().pokemonService
) {
    private fun <T> executeRequest(
        call: Call<T>,
        whenSuccess: (newPokemon: T?) -> Unit,
        whenFail: (erro: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.i("CALLAPI", "WebClient UEBA: " + response)

                if (response.isSuccessful) {
                    Log.i("CALLAPI", "WebClient Success: ")
                    whenSuccess(response.body())
                } else {
                    Log.i("CALLAPI", "WebClient Fail: ")
                    Log.e("POKEMON_API", "Erro: " + response.errorBody())
                    whenFail(UNSUCCESSFUL_REQUEST)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                whenFail(t.message)
            }
        })
    }

    fun getAll(
        limit: Int = 151,
        whenSuccess: (newPokemon: PokemonsApiResult?) -> Unit,
        whenFail: (erro: String?) -> Unit
    ) {
        executeRequest(
            service.getAll(limit),
            whenSuccess,
            whenFail
        )
    }
}

