package br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.Pokemon
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.PokemonsApiResult
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository.PokemonRepository
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository.Resource

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {
    fun getAll(): LiveData<Resource<PokemonsApiResult>?> {
        Log.i("CALLAPI", "GetAll ViewModel: ")
        return repository.getAll()
    }
}