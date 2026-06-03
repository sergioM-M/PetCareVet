package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.repository.PetRepository

class DeletePetUseCase(private val repository: PetRepository) {
    suspend operator fun invoke(id: Int) = repository.deletePet(id)
}
