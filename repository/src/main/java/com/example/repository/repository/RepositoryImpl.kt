package com.example.repository.repository

import com.example.model.models.Word
import com.example.datasource.datasource.base.DataSource
import com.example.repository.repository.base.Repository

class RepositoryImpl(
    private val dataSource: DataSource<List<Word>>,
) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
