package edu.pablogj0397.marvelapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import edu.pablogj0397.marvelapp.features.superheroes.domain.GetSuperHeroUseCase
import edu.pablogj0397.marvelapp.features.superheroes.presentation.SuperHeroListViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SuperHeroListViewModel by viewModels(

    )

    /**
     * Ciclo de vida: Paso 1 OnCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("MainActivity", "OnCreate")



         initObserver()

        viewModel.loadSuperHeroes()
    }

    private fun initObserver(){
        viewModel.uiState.observe(this){ uiState ->
            bind(uiState)
        }
    }

    /**
     * Se recoge la informacion del viewModel
     */
    private fun bind(uiState: SuperHeroListViewModel.UiState){
        /** Comprobar los estados de la vista **/
        if (uiState.isLoading) {
            //show loading
        } else {
            //hide loading
        }

        if (uiState.hasError){
            // show error view
        } else {
            // hide error view
        }

        uiState.superHeroes?.let{ superHeroes ->
            if (superHeroes.isEmpty()){
                //Muestro pantalla de listado vacio
            } else {
                //Muestro el listado de superheroes
                //Montar la info en un recyclerView
            }
        }
    }
}