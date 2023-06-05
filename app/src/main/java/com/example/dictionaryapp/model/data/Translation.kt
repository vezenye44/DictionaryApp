package com.example.dictionaryapp.model.data

import com.google.gson.annotations.SerializedName

data class Translation(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("note") val aboutTranslation: String?,
)
