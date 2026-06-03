package com.example.petcarevet.data.local.mapper

import com.example.petcarevet.data.local.entity.PetEntity
import com.example.petcarevet.domain.model.Pet

fun PetEntity.toDomain(): Pet = Pet(
    id = id,
    name = name,
    type = type,
    careDescription = careDescription,
    imageUrl = imageUrl

)

fun Pet.toEntity(): PetEntity = PetEntity(
    id = id,
    name = name,
    type = type,
    careDescription = careDescription,
    imageUrl = imageUrl
)
