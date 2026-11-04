package edu.pablogj0397.marvelapp.features.superheroes.data

import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHero
import edu.pablogj0397.marvelapp.features.superheroes.domain.SuperHeroRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SuperheroDataRepository : SuperHeroRepository {
    override fun getAll(): List<SuperHero> {
        TODO("Not yet implemented")
    }

}