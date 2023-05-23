package com.example.dictionaryapp.model.datasource.remote

import com.example.dictionaryapp.model.data.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<Word>
}