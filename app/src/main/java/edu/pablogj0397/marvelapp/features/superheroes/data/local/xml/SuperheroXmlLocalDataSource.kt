package edu.pablogj0397.marvelapp.features.superheroes.data.local.xml


import android.content.Context
import edu.pablogj0397.marvelapp.R
import edu.pablogj0397.marvelapp.core.cache.AppCache
import edu.pablogj0397.marvelapp.core.serialization.JsonSerializer
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero

/**
 * This is where the data access layer begins.
 * This acts as a connector between the repository and the data source.
 * Connects to manage data in an XML file using SharedPreferences.
 */

class SuperheroXmlLocalDataSource(
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>,
    private val appCache: AppCache
) {
    private val superheroKey = "superhero"
    private val ttl = 5 * 60 * 1000L

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    fun findAll(): List<SuperHero> {
        if (!appCache.isExpired(superheroKey, ttl)) {
            sharedPref.getString(superheroKey, null)?.let {
                return serializer.fromJson(it)
            }
        }
        return emptyList()
    }

    fun findById(id: String): SuperHero? {
        return findAll().find { it.id == id }
    }

    fun save(superheroes: List<SuperHero>) {
        sharedPref.edit().apply {
            putString(superheroKey, serializer.toJson(superheroes))
            apply()
        }
        appCache.updateTime(superheroKey)
    }


    fun deleteById(id: String) {
        save(findAll().filterNot { it.id == id })
    }

    fun deleteAll() {
        sharedPref.edit().apply {
            remove(superheroKey)
            apply()
        }
    }

    fun update(superHero: SuperHero){
        // superheroes contiene todos los superHeroes EXCEPTO el que quiero modificar.
        val superheroes = findAll().filterNot { it.id == superHero.id }.toMutableList()
        superheroes.add(superHero)
        save(superheroes)
    }
}
