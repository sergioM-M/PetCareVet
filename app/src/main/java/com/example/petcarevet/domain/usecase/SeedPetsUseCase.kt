package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.repository.PetRepository

class SeedPetsUseCase(private val repository: PetRepository) {
    suspend operator fun invoke() {
        if (repository.countPets() > 0) return
        val initialPets = listOf(
            Pet(
                name = "Luna",
                type = "Gato",
                careDescription = "Revisión anual, cepillado frecuente y control de alimentación.",
                imageUrl = "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"
            ),
            Pet(
                name = "Max",
                type = "Perro",
                careDescription = "Paseos diarios, vacunación al día y control antiparasitario.",
                imageUrl = "https://images.dog.ceo/breeds/labrador/n02099712_7415.jpg"
            ),
            Pet(
                name = "Nala",
                type = "Gato",
                careDescription = "Agua limpia, arenero cuidado y vigilancia de cambios de conducta.",
                imageUrl = "https://cdn2.thecatapi.com/images/bpc.jpg"
            )
        )
        initialPets.forEach { repository.addPet(it) }
    }
}
