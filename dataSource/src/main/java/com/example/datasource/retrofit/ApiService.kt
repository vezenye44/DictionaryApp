package com.example.datasource.retrofit

import com.example.model.models.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<Word>
}