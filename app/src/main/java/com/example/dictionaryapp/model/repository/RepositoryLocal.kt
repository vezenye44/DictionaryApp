package com.example.dictionaryapp.model.repository

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(entity: T)
}