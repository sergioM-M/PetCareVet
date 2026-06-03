package com.example.petcarevet.ui.pets.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarevet.domain.usecase.AddPetUseCase
import com.example.petcarevet.domain.usecase.GetPetByIdUseCase
import com.example.petcarevet.domain.usecase.UpdatePetUseCase

class PetFormViewModelFactory(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val addPetUseCase: AddPetUseCase,
    private val updatePetUseCase: UpdatePetUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PetFormViewModel(getPetByIdUseCase, addPetUseCase, updatePetUseCase) as T
    }
}
