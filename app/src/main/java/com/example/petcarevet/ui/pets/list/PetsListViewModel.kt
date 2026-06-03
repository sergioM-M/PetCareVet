package com.example.petcarevet.ui.pets.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarevet.data.local.session.UserRole
import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.usecase.DeleteAllPetsUseCase
import com.example.petcarevet.domain.usecase.DeletePetUseCase
import com.example.petcarevet.domain.usecase.GetPetsUseCase
import com.example.petcarevet.domain.usecase.SeedPetsUseCase
import kotlinx.coroutines.launch

class PetsListViewModel(
    private val getPetsUseCase: GetPetsUseCase,
    private val deletePetUseCase: DeletePetUseCase,
    private val deleteAllPetsUseCase: DeleteAllPetsUseCase,
    private val seedPetsUseCase: SeedPetsUseCase,
    val role: UserRole
) : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> = _pets

    init {
        viewModelScope.launch {
            seedPetsUseCase()
        }
        viewModelScope.launch {
            getPetsUseCase().collect { _pets.value = it }
        }
    }

    fun deletePet(id: Int) {
        viewModelScope.launch {
            deletePetUseCase(id)
        }
    }

    fun deleteAllPets() {
        if (role != UserRole.ADMIN) return
        viewModelScope.launch {
            deleteAllPetsUseCase()
        }
    }
}
