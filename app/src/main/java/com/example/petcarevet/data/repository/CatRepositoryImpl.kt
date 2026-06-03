package com.example.petcarevet.data.repository

import com.example.petcarevet.BuildConfig
import com.example.petcarevet.data.remote.api.CatApiService
import com.example.petcarevet.data.remote.mapper.toDomain
import com.example.petcarevet.domain.model.CatImage
import com.example.petcarevet.domain.repository.CatRepository

class CatRepositoryImpl(
    private val service: CatApiService
) : CatRepository {

    override suspend fun getCatImages(limit: Int): List<CatImage> {
        return service.getCatImages(
            apiKey = BuildConfig.CAT_API_KEY,
            limit = limit
        ).map { it.toDomain() }
    }
}
