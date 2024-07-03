package com.example.swapptest.domain.entity

data class Person(
    val name: String? = null,
    val height: Int? = null,
    val mass: Int? = null,
    val hair_color: String? = null,
    val skin_color: String? = null,
    val eye_color: String? = null,
    val birth_year: String? = null,
    val gender: String? = null,
    val homeworld: String? = null,
    val films: List<String>? = null,
    val species: List<String>? = null,
    val vehicles: List<String>? = null,
    val starships: List<String>? = null,
    val created: String? = null,
    val edited: String? = null,
    val url: String? = null
)
