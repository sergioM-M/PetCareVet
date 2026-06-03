package com.example.petcarevet.ui.cats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarevet.domain.usecase.GetCatImagesUseCase

class CatApiViewModelFactory(
    private val getCatImagesUseCase: GetCatImagesUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatApiViewModel(getCatImagesUseCase) as T
    }
}
