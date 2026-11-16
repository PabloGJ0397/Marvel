package edu.pablogj0397.marvelapp.features.superheroes.data

import edu.pablogj0397.marvelapp.features.superheroes.data.local.xml.SuperheroXmlLocalDataSource
import edu.pablogj0397.marvelapp.features.superheroes.data.remote.mock.SuperHeroMockRemoteDataSource
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHeroRepository

/**
 * Clase encargada de gestionar la fuente de datos: xml, base de datos, API, etc.
 */
class SuperheroDataRepository (private val xmlLocalDataSource: SuperheroXmlLocalDataSource,
    private val superHeroMockRemoteDataSource: SuperHeroMockRemoteDataSource
):
    SuperHeroRepository {
    override fun getAll(): List<SuperHero> {
       return xmlLocalDataSource.findAll()
    }

    override fun update(superHero: SuperHero) {
        xmlLocalDataSource.save(superHeroMockRemoteDataSource.obtainAll())
        xmlLocalDataSource.update(superHero)
    }

}