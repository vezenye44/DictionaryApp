package com.example.dictionaryapp.model.datasource.base

interface DataSource<T> {

    suspend fun getData(word: String): T
}