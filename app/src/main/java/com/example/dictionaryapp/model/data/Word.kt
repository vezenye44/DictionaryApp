package com.example.dictionaryapp.model.data

import com.google.gson.annotations.SerializedName

data class Word(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?,
)