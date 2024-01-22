package com.example.repository.repository.base

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(entity: T)
}