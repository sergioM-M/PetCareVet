package com.example.petcarevet.ui.pets.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarevet.domain.model.Pet
import com.example.petcarevet.domain.usecase.AddPetUseCase
import com.example.petcarevet.domain.usecase.GetPetByIdUseCase
import com.example.petcarevet.domain.usecase.UpdatePetUseCase
import kotlinx.coroutines.launch

class PetFormViewModel(
    private val getPetByIdUseCase: GetPetByIdUseCase,
    private val addPetUseCase: AddPetUseCase,
    private val updatePetUseCase: UpdatePetUseCase
) : ViewModel() {

    private val _pet = MutableLiveData<Pet?>()
    val pet: LiveData<Pet?> = _pet

    private val _saved = MutableLiveData<Boolean>()
    val saved: LiveData<Boolean> = _saved

    fun loadPet(id: Int) {
        if (id == -1) {
            _pet.value = null
            return
        }
        viewModelScope.launch {
            _pet.value = getPetByIdUseCase(id)
        }
    }

    fun savePet(id: Int, name: String, type: String, careDescription: String, imageUrl: String): Boolean {
        if (name.isBlank() || type.isBlank() || careDescription.isBlank() || imageUrl.isBlank()) {
            return false
        }

        viewModelScope.launch {
            val pet = Pet(
                id = if (id == -1) 0 else id,
                name = name.trim(),
                type = type,
                careDescription = careDescription.trim(),
                imageUrl = imageUrl.trim()
            )

            if (id == -1) {
                addPetUseCase(pet)
            } else {
                updatePetUseCase(pet)
            }
            _saved.value = true
        }
        return true
    }
}
