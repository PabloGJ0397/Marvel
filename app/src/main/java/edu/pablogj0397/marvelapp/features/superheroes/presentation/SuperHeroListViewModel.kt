package edu.pablogj0397.marvelapp.features.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.pablogj0397.marvelapp.features.superheroes.domain.GetSuperHeroUseCase
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero

/**
 * Esta clase se llama ViewModel porque utilizamos el patrón Model View ViewModel. Se define porque el ViewModel controla el ciclo de vida de la vista en una dinámica OneShot
 * Otros patrones (MVC - Model View Controler, MVI - Model View Intent (React), MVP - Model View Presenter // Observer --> LiveData)
 * Son todos patrones de diseño que definen cómo se conectan la parte lógica y visual.
 */

class SuperHeroListViewModel () :
    ViewModel() { //Usamos un extends viewModel porque viewModel de Android sobrevive aunque el ciclo de vida de la activity se destruya

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun loadSuperHeroes() {
        //1. Mostramos el loading..
        _uiState.value = UiState(isLoading = true)

        //2. Mostramos datos
        _uiState.value = UiState(superHeroes = emptyList())

    }
    data class UiState(
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val superHeroes: List<SuperHero>? = null

    ){

    }
}