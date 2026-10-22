package edu.pablogj0397.marvelapp.features.superheroes.domain

interface SuperHeroRepository {
    fun getAll():List<SuperHero>
}