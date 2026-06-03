package com.example.petcarevet.domain.repository

import com.example.petcarevet.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun getPets(): Flow<List<Pet>>
    suspend fun getPetById(id: Int): Pet?
    suspend fun addPet(pet: Pet)
    suspend fun updatePet(pet: Pet)
    suspend fun deletePet(id: Int)
    suspend fun deleteAllPets()
    suspend fun countPets(): Int
}
