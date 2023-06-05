package com.example.dictionaryapp.model.repository.base

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(entity: T)
}