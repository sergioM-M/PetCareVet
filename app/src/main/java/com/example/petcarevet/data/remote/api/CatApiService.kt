package com.example.petcarevet.data.remote.api

import com.example.petcarevet.data.remote.dto.CatImageDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getCatImages(
        @Header("x-api-key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("size") size: String = "med",
        @Query("mime_types") mimeTypes: String = "jpg,png",
        @Query("format") format: String = "json"
    ): List<CatImageDto>
}
