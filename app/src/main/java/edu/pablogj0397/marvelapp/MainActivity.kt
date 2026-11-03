package edu.pablogj0397.marvelapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.pablogj0397.marvelapp.features.superheroes.data.local.xml.SuperheroXmlLocalDataSource
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val xml = SuperheroXmlLocalDataSource(this)

        xml.save(mutableListOf(
            SuperHero("1", "SuperMan", "super-man", "https//man"),
            SuperHero("2", "SuperWoman", "super-woman", "https//woman"),
            SuperHero("3", "SuperDog", "super-dog", "https//dog"),
        ))

    }
}