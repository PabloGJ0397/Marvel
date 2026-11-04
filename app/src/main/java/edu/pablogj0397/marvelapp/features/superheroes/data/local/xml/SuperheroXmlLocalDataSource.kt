package edu.pablogj0397.marvelapp.features.superheroes.data.local.xml


import android.content.Context
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import edu.pablogj0397.marvelapp.core.cache.CacheTTL
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero

/**
 * This is where the data access layer begins.
 * This acts as a connector between the repository and the data source.
 * Connects to manage data in an XML file using SharedPreferences.
 */

class SuperheroXmlLocalDataSource(
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>,
    private val cacheTTL: CacheTTL = CacheTTL(context, "superHero_cache", 5*60*1000)
) {
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    // aquí hemos decidido que en caso de no existir, devolvemos null
    public fun findAll(): List<SuperHero>? {
        val json = sharedPref.getString(keySuperHero, null)
        return if (!cacheTTL.isExpired()) json?.let {
            serializer.fromJson(it)
        } else {
            null
        }
    }

    public fun findById(id: String): SuperHero? {
        val superheroJsonList = findAll()
        return superheroJsonList?.find { it.id == id }
    }

    public fun save(superheroes: List<SuperHero>) {
        sharedPref.edit().apply {
            putString(keySuperHero, serializer.toJson(superheroes))
            apply()
            cacheTTL.saveCurrentTime()
        }

    }

    fun deleteById(id: String) {
        findAll()?.let {
            val updatedList = it.filterNot { element -> element.id == id }
            save(updatedList)

        }


    }

    fun deleteAll() {
        sharedPref.edit().apply {
            remove(keySuperHero)
            apply()
        }
        cacheTTL.saveCurrentTime()
    }
    companion object {
        private val keySuperHero: String = "SuperHero"
    }


}