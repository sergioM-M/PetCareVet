package com.example.petcarevet.domain.repository

import com.example.petcarevet.domain.model.CatImage

interface CatRepository {
    suspend fun getCatImages(limit: Int): List<CatImage>

}
