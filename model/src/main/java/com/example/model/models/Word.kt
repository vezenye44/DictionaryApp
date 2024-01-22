package com.example.model.models

data class Word(
    val text: String = "",
    val meanings: List<Meanings> = listOf(),
)