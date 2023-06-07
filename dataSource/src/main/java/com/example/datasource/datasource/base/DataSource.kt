package com.example.datasource.datasource.base

interface DataSource<T> {

    suspend fun getData(word: String): T
}