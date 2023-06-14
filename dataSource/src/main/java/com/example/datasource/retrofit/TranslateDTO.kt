package com.example.datasource.retrofit

import com.google.gson.annotations.SerializedName


data class MeaningsDTO(
    @field:SerializedName("translation") val translation: TranslationDTO?,
    @field:SerializedName("imageUrl") val imageUrl: String?,
)

data class TranslationDTO(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("note") val aboutTranslation: String?,
)

data class WordDTO(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<MeaningsDTO>?,
)
