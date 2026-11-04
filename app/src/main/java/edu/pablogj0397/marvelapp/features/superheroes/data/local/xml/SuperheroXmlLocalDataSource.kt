package edu.pablogj0397.marvelapp.features.superheroes.data.local.xml


import android.content.Context
import com.google.gson.Gson
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero

/**
 * This is where the data access layer begins.
 * This acts as a connector between the repository and the data source.
 * Connects to manage data in an XML file using SharedPreferences.
 */

class SuperheroXmlLocalDataSource(
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>
) {
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    // aquí hemos decidido que en caso de no existir, devolvemos null
    public fun findAll(): List<SuperHero>? {
        val json = sharedPref.getString(keySuperHero, null)
        return json?.let {
            serializer.fromJson(it)
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
    }
    companion object {
        private val keySuperHero: String = "SuperHero"
    }


}