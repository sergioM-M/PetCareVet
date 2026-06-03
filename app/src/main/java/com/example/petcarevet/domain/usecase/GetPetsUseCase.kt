package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.repository.PetRepository

class GetPetsUseCase(private val repository: PetRepository) {
    operator fun invoke() = repository.getPets()
}
