package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.datasource.base.DataSourceLocal
import com.example.dictionaryapp.model.repository.base.RepositoryLocal

class RepositoryLocalImpl(
    private val dataSource: DataSourceLocal<List<Word>>
): RepositoryLocal<List<Word>> {
    override suspend fun saveToDB(entity: List<Word>) {
        dataSource.saveToDB(entity)
    }

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}