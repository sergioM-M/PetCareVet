package com.example.petcarevet.domain.model

data class Pet(
    val id: Int = 0,
    val name: String,
    val type: String,
    val careDescription: String,
    val imageUrl: String
)
