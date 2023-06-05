package com.example.dictionaryapp.model.repository.base

interface Repository<T> {

    suspend fun getData(word: String): T
}