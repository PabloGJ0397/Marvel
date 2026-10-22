package edu.pablogj0397.marvelapp.features.superheroes.domain

class GetSuperHeroUseCase(private val superHeroRepository: SuperHeroRepository) {
    operator fun invoke(): List<SuperHero>{
        return superHeroRepository.getAll()
    }
}