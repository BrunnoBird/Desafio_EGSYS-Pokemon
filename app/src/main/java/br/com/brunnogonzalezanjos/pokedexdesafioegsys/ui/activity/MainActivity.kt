package br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.R
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository.PokemonRepository
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.PokemonListViewModel
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.factory.PokemonListViewModelFactory

//PAREI EM 1:16 minutos -> https://www.youtube.com/watch?v=RVfEqMWi7x8&t=4982s&ab_channel=PauloSalvatore

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val repository = PokemonRepository()
        val factory = PokemonListViewModelFactory(repository)
        ViewModelProvider(this, factory).get(PokemonListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findPokemons()
    }

    private fun findPokemons() {
        Log.i("CALLAPI", "Cliquei: ")
        viewModel.getAll().observe(this, Observer { resource ->
            resource?.data?.let { pokemons ->
                //atualiza adapter
                Log.i("CALLAPI", "findPokemons: " + pokemons)
            }
        })
    }
}