package com.example.petcarevet.ui.cats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarevet.domain.usecase.GetCatImagesUseCase
import kotlinx.coroutines.launch

class CatApiViewModel(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CatApiUiState>(CatApiUiState.Idle)
    val state: LiveData<CatApiUiState> = _state

    fun searchCats(limitText: String) {
        val limit = limitText.toIntOrNull()
        if (limit == null || limit !in 1..25) {
            _state.value = CatApiUiState.Error("Introduce un número entre 1 y 25.")
            return
        }

        viewModelScope.launch {
            _state.value = CatApiUiState.Loading
            try {
                val images = getCatImagesUseCase(limit)
                _state.value = CatApiUiState.Success(images)
            } catch (exception: Exception) {
                _state.value = CatApiUiState.Error("No se han podido cargar las imágenes: ${exception.message}")
            }
        }
    }
}
