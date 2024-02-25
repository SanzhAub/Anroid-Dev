package com.example.lab2

data class Animal(
    val characteristics: Characteristics,
    val locations: List<String>,
    val name: String,
    val taxonomy: Taxonomy
) {
    fun convertToMyDataItem(): MyDataItem {
        return MyDataItem(
            characteristics = this.characteristics,
            locations = this.locations,
            name = this.name,
            taxonomy = this.taxonomy
        )
    }
}