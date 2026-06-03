package com.example.petcarevet.domain.usecase

import com.example.petcarevet.domain.repository.CatRepository

class GetCatImagesUseCase(private val repository: CatRepository) {
    suspend operator fun invoke(limit: Int) = repository.getCatImages(limit)
}
