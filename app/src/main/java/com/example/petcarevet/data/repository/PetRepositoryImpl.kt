package com.example.petcarevet.data.repository

import com.example.petcarevet.data.local.dao.PetDao
import com.example.petcarevet.data.local.mapper.toDomain
import com.example.petcarevet.data.local.mapper.toEntity
import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PetRepositoryImpl(
    private val petDao: PetDao
) : PetRepository {


    override fun getPets(): Flow<List<Pet>> {
        return petDao.getAllPets().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getPetById(id: Int): Pet? {
        return petDao.getPetById(id)?.toDomain()
    }

    override suspend fun addPet(pet: Pet) {
        petDao.insertPet(pet.copy(id = 0).toEntity())
    }

    override suspend fun updatePet(pet: Pet) {
        petDao.updatePet(pet.toEntity())
    }

    override suspend fun deletePet(id: Int) {
        petDao.deletePetById(id)
    }

    override suspend fun deleteAllPets() {
        petDao.deleteAllPets()
    }

    override suspend fun countPets(): Int {
        return petDao.countPets()
    }
}
