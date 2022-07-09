package br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.R
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.model.Pokemon
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository.PokemonRepository
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.recyclerview.PokemonListAdapter
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.PokemonListViewModel
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.factory.PokemonListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        val repository = PokemonRepository
        val factory = PokemonListViewModelFactory(repository)
        ViewModelProvider(this, factory).get(PokemonListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configFab()
        findPokemons()
    }

    private fun configFab() {
        fab_random_pokemon.setOnClickListener {
            randomPokemon()
        }
    }

    private fun randomPokemon() {
        Log.i("Cliquei", "randomPokemon: Cliquei")
    }

    private fun loadRecyclerView(pokemons: MutableList<Pokemon?>) {
        activity_list_pokemon_recyclerview.adapter = PokemonListAdapter(
            this, pokemons,
            onItemClick = this::openPokemonDetails
        )
    }

    private fun openPokemonDetails(it: Pokemon) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(POKEMON_ID, it.number)
        startActivity(intent)
    }

    private fun findPokemons() {
        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it as MutableList<Pokemon?>)
        })
    }
}