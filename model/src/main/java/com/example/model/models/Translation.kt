package com.example.model.models

import com.google.gson.annotations.SerializedName

data class Translation(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("note") val aboutTranslation: String?,
)
