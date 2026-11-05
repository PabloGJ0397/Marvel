package edu.pablogj0397.marvelapp.features.superheroes.data

import edu.pablogj0397.marvelapp.features.superheroes.data.local.xml.SuperheroXmlLocalDataSource
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHeroRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Clase encargada de gestionar la fuente de datos: xml, base de datos, API, etc.
 */
class SuperheroDataRepository (private val xmlLocalDataSource: SuperheroXmlLocalDataSource): SuperHeroRepository {
    override fun getAll(): List<SuperHero>? {
       return xmlLocalDataSource.findAll()
    }

}