package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.datasource.base.DataSource
import com.example.dictionaryapp.model.repository.base.Repository

class RepositoryImpl(
    private val dataSource: DataSource<List<Word>>,
) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
