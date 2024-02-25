package com.example.lab2

data class AnimalItem(
    val characteristics: Characteristics,
    val locations: List<String>,
    val name: String,
    val taxonomy: Taxonomy
)