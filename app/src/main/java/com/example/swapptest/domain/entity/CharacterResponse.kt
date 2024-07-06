package com.example.swapptest.domain.entity

data class CharacterResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Character>
)
