package com.example.petcarevet.ui.pets.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.usecase.GetPetByIdUseCase
import kotlinx.coroutines.launch

class PetDetailViewModel(
    private val getPetByIdUseCase: GetPetByIdUseCase
) : ViewModel() {

    private val _pet = MutableLiveData<Pet?>()
    val pet: LiveData<Pet?> = _pet

    fun loadPet(id: Int) {
        viewModelScope.launch {
            _pet.value = getPetByIdUseCase(id)
        }
    }
}
