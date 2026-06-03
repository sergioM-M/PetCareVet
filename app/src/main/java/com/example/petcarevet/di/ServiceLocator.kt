package com.example.petcarevet.di

import android.content.Context
import com.example.petcarevet.data.local.database.PetDatabase
import com.example.petcarevet.data.local.session.SessionDataSource
import com.example.petcarevet.data.remote.api.CatApiClient
import com.example.petcarevet.data.repository.CatRepositoryImpl
import com.example.petcarevet.data.repository.PetRepositoryImpl
import com.example.petcarevet.domain.repository.CatRepository
import com.example.petcarevet.domain.repository.PetRepository
import com.example.petcarevet.domain.usecase.*

object ServiceLocator {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private val database: PetDatabase by lazy {
        PetDatabase.getDatabase(appContext)
    }

    val sessionDataSource: SessionDataSource by lazy {
        SessionDataSource(appContext)
    }

    val petRepository: PetRepository by lazy {
        PetRepositoryImpl(database.petDao())
    }

    val catRepository: CatRepository by lazy {
        CatRepositoryImpl(CatApiClient.service)
    }

    val getPetsUseCase: GetPetsUseCase by lazy { GetPetsUseCase(petRepository) }
    val getPetByIdUseCase: GetPetByIdUseCase by lazy { GetPetByIdUseCase(petRepository) }
    val addPetUseCase: AddPetUseCase by lazy { AddPetUseCase(petRepository) }
    val updatePetUseCase: UpdatePetUseCase by lazy { UpdatePetUseCase(petRepository) }
    val deletePetUseCase: DeletePetUseCase by lazy { DeletePetUseCase(petRepository) }
    val deleteAllPetsUseCase: DeleteAllPetsUseCase by lazy { DeleteAllPetsUseCase(petRepository) }
    val seedPetsUseCase: SeedPetsUseCase by lazy { SeedPetsUseCase(petRepository) }
    val getCatImagesUseCase: GetCatImagesUseCase by lazy { GetCatImagesUseCase(catRepository) }
}
