package com.example.petcarevet.ui.cats

import com.example.petcarevet.domain.model.CatImage

sealed class CatApiUiState {
    data object Idle : CatApiUiState()
    data object Loading : CatApiUiState()
    data class Success(val images: List<CatImage>) : CatApiUiState()
    data class Error(val message: String) : CatApiUiState()
}
