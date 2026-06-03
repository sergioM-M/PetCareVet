package com.example.petcarevet.ui.pets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarevet.data.local.session.UserRole
import com.example.petcarevet.domain.usecase.DeleteAllPetsUseCase
import com.example.petcarevet.domain.usecase.DeletePetUseCase
import com.example.petcarevet.domain.usecase.GetPetsUseCase
import com.example.petcarevet.domain.usecase.SeedPetsUseCase

class PetsListViewModelFactory(
    private val getPetsUseCase: GetPetsUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val deleteAllPetsUseCase: DeleteAllPetsUseCase,
    private val seedPetsUseCase: SeedPetsUseCase,
    private val role: UserRole
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetsListViewModel(
            getPetsUseCase,
            deletePetUseCase,
            deleteAllPetsUseCase,
            seedPetsUseCase,
            role
        ) as T
    }
}
