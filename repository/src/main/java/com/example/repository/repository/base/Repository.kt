package com.example.repository.repository.base

interface Repository<T> {

    suspend fun getData(word: String): T
}