package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.repository.PetRepository

class UpdatePetUseCase(private val repository: PetRepository) {
    suspend operator fun invoke(pet: Pet) = repository.updatePet(pet)
}
