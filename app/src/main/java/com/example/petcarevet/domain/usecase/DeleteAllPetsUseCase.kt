package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.repository.PetRepository

class DeleteAllPetsUseCase(private val repository: PetRepository) {
    suspend operator fun invoke() = repository.deleteAllPets()
}
