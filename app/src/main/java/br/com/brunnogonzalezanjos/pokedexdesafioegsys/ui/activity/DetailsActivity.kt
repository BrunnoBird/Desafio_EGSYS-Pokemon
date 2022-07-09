package br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.R
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.repository.PokemonRepository
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.DetailsViewModel
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.PokemonListViewModel
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.factory.DetailsViewModelFactory
import br.com.brunnogonzalezanjos.pokedexdesafioegsys.ui.viewmodel.factory.PokemonListViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_card_pokemon.view.*

class DetailsActivity : AppCompatActivity() {

    private val pokemonId: Long by lazy {
        intent.getLongExtra(POKEMON_ID, 0)
    }

    private val viewModel by lazy {
        val repository = PokemonRepository
        val factory = DetailsViewModelFactory(pokemonId, repository)
        ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding()
    }

    private fun binding() {
        viewModel.pokemon.observe(this, Observer {
            it?.let {
                Glide.with(ivPokemonImageDetail.context).load(it.imageUrl)
                    .into(ivPokemonImageDetail)
                tvPokemonNameDetail.text = it.name
                tvNumberPokemonDetail.text = "#${it.number.toString()}"
                tvPokemonDetailHpPoints.text = it.hp.toString()
                progressBarPokemonDetailHp.progress = it.hp
                tvPokemonDetailAtkPoints.text = it.attack.toString()
                progressBarPokemonDetailAtk.progress = it.attack
                tvPokemonDetailDefPoints.text = it.defense.toString()
                progressBarPokemonDetailDef.progress = it.defense

                tvPokemonDetatilElement.text = it.types[0].name.capitalize()
                if (it.types.size > 1) {
                    tvPokemonDetatilElement2.visibility = View.VISIBLE
                    tvPokemonDetatilElement2.text = it.types[1].name.capitalize()
                } else {
                    tvPokemonDetatilElement2.visibility = View.GONE
                }
            }

        })
    }
}