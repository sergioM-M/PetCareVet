package com.example.petcarevet.ui.pets.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarevet.domain.usecase.GetPetByIdUseCase

class PetDetailViewModelFactory(
    private val getPetByIdUseCase: GetPetByIdUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetDetailViewModel(getPetByIdUseCase) as T
    }
}
