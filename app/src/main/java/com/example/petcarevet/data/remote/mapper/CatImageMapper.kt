package com.example.petcarevet.data.remote.mapper

import com.example.petcarevet.data.remote.dto.CatImageDto
import com.example.petcarevet.domain.model.CatImage

fun CatImageDto.toDomain(): CatImage = CatImage(
    id = id,
    url = url
)
